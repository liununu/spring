package me.liununu.springdata.query.finder

import me.liununu.springdata.query.Assignments

sealed interface AssignmentsFinder {

    val name: Name

    fun findAll(): Assignments

    enum class Name {
        NOOP,
        NOT_IMPLEMENTED,
        JPA_CRITERIA,
    }
}
