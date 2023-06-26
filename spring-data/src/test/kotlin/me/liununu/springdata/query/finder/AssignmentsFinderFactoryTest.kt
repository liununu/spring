package me.liununu.springdata.query.finder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AssignmentsFinderFactoryTest(@Autowired private val factory: AssignmentsFinderFactory) {

    @Test
    fun `should return not implement finder by default`() {
        val finder = factory.get()

        assertThat(finder).isExactlyInstanceOf(NotImplementedFinder::class.java)
    }
}
