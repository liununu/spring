package me.liununu.springdata.query.finder

import org.springframework.stereotype.Component

@Component
class AssignmentsFinderFactory(private val finders: List<AssignmentsFinder>) {
    fun get(): AssignmentsFinder {
        return finders.find { it::class.java.simpleName == "NotImplementedFinder" }!!
    }

}
