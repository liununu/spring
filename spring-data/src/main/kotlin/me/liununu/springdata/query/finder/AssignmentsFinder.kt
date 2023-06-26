package me.liununu.springdata.query.finder

import me.liununu.springdata.query.Assignments

interface AssignmentsFinder {
    fun findAll(): Assignments
}
