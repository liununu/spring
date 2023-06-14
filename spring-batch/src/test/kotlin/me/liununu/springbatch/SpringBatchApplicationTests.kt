package me.liununu.springbatch

import me.liununu.springbatch.output.ClientRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@SpringBatchTest
@TestPropertySource(properties = ["spring.batch.job.enabled=false"])
class SpringBatchApplicationTests(
    @Autowired private val jobLauncherTestUtils: JobLauncherTestUtils,
    @Autowired private val repository: ClientRepository
) {

    @Test
    fun `Spring Batch Job E2E Test`() {
        val parameters = JobParametersBuilder()
            .addString("input.path", "MOCK_DATA.csv")
            .toJobParameters()

        val execution = jobLauncherTestUtils.launchJob(parameters)

        val count = repository.count()
        assertThat(count).isEqualTo(100)
        assertThat(execution.exitStatus.exitCode).isEqualTo("COMPLETED")
    }

}
