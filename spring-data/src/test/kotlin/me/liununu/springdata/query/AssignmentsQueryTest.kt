package me.liununu.springdata.query

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junitpioneer.jupiter.SetEnvironmentVariable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class AssignmentsQueryTest(@Autowired private val query: AssignmentsQuery) {
    @Test
    @SetEnvironmentVariable(value = "NOOP", key = "ACTIVE_FINDER")
    fun `should return empty when find all given noop finder`() {
        val assignments = query.findAll()

        assertThat(assignments).isEmpty()
    }

    @Test
    fun `should throw when find all given not implemented finder`() {
        assertThatThrownBy { query.findAll() }
            .isExactlyInstanceOf(NotImplementedError::class.java)
            .hasMessage("Not yet implemented")
    }

    @Test
    @SetEnvironmentVariable(value = "JPA_CRITERIA", key = "ACTIVE_FINDER")
    fun `should return assignments when find all given JPA criteria finder`() {
        val assignments = query.findAll()

        assertThat(assignments).hasSize(30)
        val employeeId = "2e181ff1-7f64-4460-9010-ff45e81c74ee"
        val assignment = assignments.find { it.id == employeeId }
        assertThat(assignment).isEqualTo(
            Assignment(
                id = employeeId,
                employeeName = "Diego Idell",
                employeeEmail = "didell18@hubpages.com",
                projectName = "Farrell, Thompson and Cronin",
                projectStartDate = LocalDate.of(2022, 10, 29),
                projectEndDate = null
            )
        )
    }
}
