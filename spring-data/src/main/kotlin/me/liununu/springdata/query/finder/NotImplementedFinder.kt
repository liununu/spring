package me.liununu.springdata.query.finder

import me.liununu.springdata.query.finder.AssignmentsFinder.Name.NOT_IMPLEMENTED
import org.springframework.stereotype.Component


@Component
class NotImplementedFinder : AssignmentsFinder {
    override val name = NOT_IMPLEMENTED

    override fun findAll() = throw NotImplementedError("Not yet implemented")
}
