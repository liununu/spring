package me.liununu.springbatch.input

import me.liununu.springbatch.input.User.Gender.FEMALE
import me.liununu.springbatch.toUTCZonedDateTime
import me.liununu.springbatch.use
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.test.MetaDataInstanceFactory
import org.springframework.batch.test.StepScopeTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.time.LocalDateTime


@SpringBatchTest
@SpringJUnitConfig(ReaderConfiguration::class)
class ReaderConfigurationTest(@Autowired private val reader: FlatFileItemReader<User>) {

    @Test
    fun `should return users when read from csv`() {
        val stepExecution = MetaDataInstanceFactory.createStepExecution()

        val users = StepScopeTestUtils.doInStepScope(stepExecution) {
            reader.use(stepExecution.executionContext) {
                generateSequence { it.read() }.toList()
            }
        }

        assertThat(users).hasSize(1)
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
    }
}
