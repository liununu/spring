package me.liununu.springdata.query.finder

import me.liununu.springdata.query.Assignment
import me.liununu.springdata.query.finder.AssignmentsFinder.Name.NOOP
import org.springframework.stereotype.Component

@Component
class NoopFinder : AssignmentsFinder {
    override val name = NOOP

    override fun findAll() = emptyList<Assignment>()
}
