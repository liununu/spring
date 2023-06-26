package me.liununu.springdata.query.finder

import org.springframework.stereotype.Component


@Component
class NotImplementedFinder : AssignmentsFinder {
    override fun findAll() = throw NotImplementedError("Not yet implemented")
}
