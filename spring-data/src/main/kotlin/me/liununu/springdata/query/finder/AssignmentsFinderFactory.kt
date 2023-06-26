package me.liununu.springdata.query.finder

import me.liununu.springdata.query.finder.AssignmentsFinder.Name.NOT_IMPLEMENTED
import org.springframework.stereotype.Component

@Component
class AssignmentsFinderFactory(private val finders: List<AssignmentsFinder>) {
    fun get(): AssignmentsFinder {
        val finderName = System.getenv("ACTIVE_FINDER")
            ?.let { AssignmentsFinder.Name.valueOf(it) }
            ?: NOT_IMPLEMENTED
        return finders.first { it.name == finderName }
    }

}
