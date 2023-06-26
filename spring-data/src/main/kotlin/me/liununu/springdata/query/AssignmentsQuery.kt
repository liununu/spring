package me.liununu.springdata.query

import me.liununu.springdata.query.finder.AssignmentsFinderFactory
import org.springframework.stereotype.Service

@Service
class AssignmentsQuery(private val finderFactory: AssignmentsFinderFactory) {
    fun findAll() = this.finderFactory.get().findAll()
}
