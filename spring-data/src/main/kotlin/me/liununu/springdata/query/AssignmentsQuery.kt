package me.liununu.springdata.query

import me.liununu.springdata.query.finder.AssignmentsFinderFactory
import me.liununu.springdata.query.finder.NoopFinder
import org.springframework.stereotype.Service

@Service
class AssignmentsQuery(private val finderFactory: AssignmentsFinderFactory, private val finder: NoopFinder) {
    fun findAll() = this.finder.findAll()
}
