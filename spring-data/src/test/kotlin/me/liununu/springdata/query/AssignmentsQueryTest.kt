package me.liununu.springdata.query

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junitpioneer.jupiter.SetEnvironmentVariable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

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

}
