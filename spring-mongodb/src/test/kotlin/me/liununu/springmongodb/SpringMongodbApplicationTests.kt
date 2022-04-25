package me.liununu.springmongodb

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
    private lateinit var repository: PersonRepository

    companion object {
        @Container
        private val container: MongoDBContainer = MongoDBContainer("mongo:latest")
            .withCommand()
            .withCopyFileToContainer(
                MountableFile.forClasspathResource("init/persons-init.js"),
                "/docker-entrypoint-initdb.d/persons-init.js"
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
        assertThat(repository.count()).isEqualTo(10)
    }

    @Test
    internal fun shouldFindPersonWithEmail() {
        val email = "dlamacraft3@google.nl"

        val person = repository.findByEmail(email)

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

        val person = repository.findByEmail(email)

        assertThat(person).isNull()
    }
}
