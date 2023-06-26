package me.liununu.springdata.query.finder.impl

import me.liununu.springdata.query.Assignment
import me.liununu.springdata.query.finder.AssignmentsFinder
import org.springframework.stereotype.Component

@Component
class NoopFinder : AssignmentsFinder {
    override fun findAll() = emptyList<Assignment>()
}
