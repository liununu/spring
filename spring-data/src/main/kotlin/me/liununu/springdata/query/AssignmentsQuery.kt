package me.liununu.springdata.query

import me.liununu.springdata.query.finder.AssignmentsFinder
import org.springframework.stereotype.Service

@Service
class AssignmentsQuery(private val finder: AssignmentsFinder) {
    fun findAll() = this.finder.findAll()
}
