package me.liununu.springcontractprovider

import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Provider("e2e Provider Example") // This name should match that defined on the consumer side
@PactBroker(host = "localhost", port = "9292")
class ContractVerificationTest {
    @LocalServerPort
    private var localPort: Int = 0

    @BeforeEach
    fun before(context: PactVerificationContext) {
        context.target = HttpTestTarget(port = localPort)
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @State("is not authenticated")
    fun `is not authenticated`() {
    }

    @State("Has some animals")
    fun `Has some animals`() {
    }

    @State("Has an animal with ID 1")
    fun `Has an animal with ID 1`() {
    }

    @State("Has no animals")
    fun `Has no animals`() {
    }
}
