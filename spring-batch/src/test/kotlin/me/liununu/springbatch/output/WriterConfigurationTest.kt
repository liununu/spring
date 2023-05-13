package me.liununu.springbatch.output

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.time.LocalDateTime
import java.time.ZoneId

@DataJpaTest
@SpringBatchTest
@EnableAutoConfiguration
@SpringJUnitConfig(WriterConfiguration::class)
class WriterConfigurationTest(
    @Autowired private val writer: ItemWriter<Client>,
    @Autowired private val repository: ClientRepository,
) {
    @Test
    fun `should save clients`() {
        val client1 = Client(
            id = 1,
            name = "Lisetta Winckles",
            email = "lwinckles0@godaddy.com",
            gender = Client.Gender.FEMALE,
            remark = "Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
            ipAddress = "209.92.122.176",
            createdAt = LocalDateTime.of(2022, 6, 21, 0, 18, 8).atZone(ZoneId.of("Asia/Hong_Kong"))
        )
        val client2 = Client(
            id = 2,
            name = "Feliza Arnson",
            email = "farnson1@shinystat.com",
            gender = Client.Gender.FEMALE,
            remark = "any remark",
            ipAddress = "190.209.15.91",
            createdAt = LocalDateTime.now().atZone(ZoneId.of("Asia/Hong_Kong"))
        )

        writer.write(Chunk.of(client1, client2))

        val count = repository.count()
        assertThat(count).isEqualTo(2)
        val actualClient1 = repository.findByIdOrNull(1)
        assertThat(actualClient1).isEqualTo(client1)
        val actualClient2 = repository.findByIdOrNull(2)
        assertThat(actualClient2).isEqualTo(client2)
    }
}
