package me.liununu.springmongodb

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class SpringMongodbApplicationTests {

    @Container
    private val container: MongoDBContainer = MongoDBContainer("mongo:latest")

    @Test
    internal fun shouldInitMongoDBContainer() {
        assertThat(container.isRunning).isTrue
    }
}
