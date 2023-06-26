package me.liununu.springdata.query.finder

import me.liununu.springdata.query.Assignment
import org.springframework.stereotype.Component

@Component
class NoopFinder : AssignmentsFinder {
    override fun findAll() = emptyList<Assignment>()
}
