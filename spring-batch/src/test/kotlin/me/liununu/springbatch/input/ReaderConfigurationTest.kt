package me.liununu.springbatch.input

import me.liununu.springbatch.input.User.Gender.FEMALE
import me.liununu.springbatch.toUTCZonedDateTime
import me.liununu.springbatch.use
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.test.MetaDataInstanceFactory
import org.springframework.batch.test.StepScopeTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.time.LocalDateTime


@SpringBatchTest
@EnableAutoConfiguration
@SpringJUnitConfig(ReaderConfiguration::class)
class ReaderConfigurationTest(@Autowired private val reader: FlatFileItemReader<User>) {

    @Test
    fun `should return users when read from csv`() {
        val stepExecution = JobParametersBuilder()
            .addString("input.path", "MOCK_DATA.csv")
            .toJobParameters()
            .let(MetaDataInstanceFactory::createStepExecution)

        val users = StepScopeTestUtils.doInStepScope(stepExecution) {
            reader.use(stepExecution.executionContext) {
                generateSequence { it.read() }.toList()
            }
        }

        assertThat(users).hasSize(100)
            .first()
            .isEqualTo(
                User(
                    id = 1,
                    firstName = "Lisetta",
                    lastName = "Winckles",
                    email = "lwinckles0@godaddy.com",
                    gender = FEMALE,
                    remark = "Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
                    ipAddress = "209.92.122.176",
                    createdAt = LocalDateTime.of(2022, 6, 20, 16, 18, 8).toUTCZonedDateTime()
                )
            )
        assertThat(users)
            .last()
            .isEqualTo(
                User(
                    id = 100,
                    firstName = "Berri",
                    lastName = "Raubenheimers",
                    email = "braubenheimers2r@furl.net",
                    gender = FEMALE,
                    remark = "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.\n" +
                            "\n" +
                            "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.",
                    ipAddress = "247.102.236.27",
                    createdAt = LocalDateTime.of(2022, 9, 10, 11, 13, 39).toUTCZonedDateTime()
                )
            )
    }
}
