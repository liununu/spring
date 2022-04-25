package me.liununu.springmongodb

import me.liununu.springmongodb.repository.AddressRepository
import me.liununu.springmongodb.repository.PersonRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.MountableFile
import java.time.Duration

@SpringBootTest
@Testcontainers
class SpringMongodbApplicationTests {
    @Autowired
    private lateinit var personRepository: PersonRepository

    @Autowired
    private lateinit var addressRepository: AddressRepository

    companion object {
        @Container
        private val container: MongoDBContainer = MongoDBContainer("mongo:latest")
            .withCommand()
            .withCopyFileToContainer(
                MountableFile.forClasspathResource("init/"),
                "/docker-entrypoint-initdb.d/"
            )
            .waitingFor(Wait.forLogMessage("(?i).*waiting for connections.*", 2))
            .withStartupTimeout(Duration.ofSeconds(10))

        @JvmStatic
        @DynamicPropertySource
        private fun mongodbProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl)
        }
    }

    @Test
    internal fun shouldInitMongoDBContainer() {
        assertThat(container.isRunning).isTrue
        assertThat(personRepository.count()).isEqualTo(10)
    }

    @Test
    internal fun shouldFindPersonWithEmail() {
        val email = "dlamacraft3@google.nl"

        val person = personRepository.findByEmail(email)

        assertThat(person).isNotNull
        assertThat(person!!.id).isNotEmpty
        assertThat(person.pid).isEqualTo("bfa55331-bb69-4f43-8d2c-a742c3cf2ac0")
        assertThat(person.firstName).isEqualTo("Dennison")
        assertThat(person.lastName).isEqualTo("Lamacraft")
        assertThat(person.email).isEqualTo(email)
    }

    @Test
    internal fun shouldNotFindPersonWithEmail() {
        val email = "non-existent-person@google.com"

        val person = personRepository.findByEmail(email)

        assertThat(person).isNull()
    }

    @Test
    internal fun shouldFindAllAddressesByCountry() {
        // given
        val country = "Brazil"

        // when
        val addresses = addressRepository.findAllByCountry(country)

        // then
        assertThat(addresses).hasSize(2)
        val addressIdToAddress = addresses.associateBy { it.aid }
        val firstAddress = addressIdToAddress.getValue("cb911f7c-de03-414d-b1fc-5ee0162cb867")
        assertThat(firstAddress.id).isNotEmpty
        assertThat(firstAddress.pid).isEqualTo("61145683-b13f-4ef4-a4c3-e33a2b5a8a35")
        assertThat(firstAddress.aid).isEqualTo("cb911f7c-de03-414d-b1fc-5ee0162cb867")
        assertThat(firstAddress.country).isEqualTo(country)
        assertThat(firstAddress.street).isEqualTo("136 Gateway Circle")
        assertThat(firstAddress.postalCode).isEqualTo("57230-000")

        val secondAddress = addressIdToAddress.getValue("5ca574ce-304b-4df9-a85e-8837280c0610")
        assertThat(secondAddress.id).isNotEmpty
        assertThat(secondAddress.pid).isEqualTo("61145683-b13f-4ef4-a4c3-e33a2b5a8a35")
        assertThat(secondAddress.aid).isEqualTo("5ca574ce-304b-4df9-a85e-8837280c0610")
        assertThat(secondAddress.country).isEqualTo(country)
        assertThat(secondAddress.street).isEqualTo("5 5th Alley")
        assertThat(secondAddress.postalCode).isNull()
    }
}
