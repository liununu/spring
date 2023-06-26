package me.liununu.springdata.query

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AssignmentsQueryTest {
    @Test
    fun `should return empty when find all given noop finder`(@Autowired query: AssignmentsQuery) {
        val assignments = query.findAll()

        assertThat(assignments).isEmpty()
    }
}
