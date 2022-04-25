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
}
