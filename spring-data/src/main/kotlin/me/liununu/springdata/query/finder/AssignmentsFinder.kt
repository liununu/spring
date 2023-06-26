package me.liununu.springdata.query.finder

import me.liununu.springdata.query.Assignments

sealed interface AssignmentsFinder {
    fun findAll(): Assignments
}
