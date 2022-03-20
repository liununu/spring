package me.liununu.springcontractprovider

import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Provider("e2e Provider Example") // This name should match that defined on the consumer side
@PactBroker(url = "http://localhost:9292")
@AutoConfigureWireMock(port = 8979) // This port should match that defined on the remote call
class ContractVerificationTest {
    @LocalServerPort
    private var localPort: Int = 0

    @Value("classpath:animalData.json")
    private lateinit var mockAnimalData: Resource

    @BeforeEach
    fun before(context: PactVerificationContext) {
        context.target = HttpTestTarget(port = localPort)
        System.setProperty("pact.verifier.publishResults", "true")

        stubFor(
            post("/animals")
                .willReturn(
                    aResponse()
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withStatus(CREATED.value())
                )
        )
    }

    @AfterEach
    fun tearDown() {
        removeAllMappings()
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @State("is not authenticated")
    fun `is not authenticated`() {
    }

    @State(value = ["Has some animals", "Has an animal with ID 1", "Has no animals"])
    fun `has animals states`() {
        stubFor(
            get("/animals")
                .willReturn(
                    aResponse()
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(mockAnimalData.file.readText())
                )
        )
    }
}
