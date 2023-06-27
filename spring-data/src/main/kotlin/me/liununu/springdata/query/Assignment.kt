package me.liununu.springdata.query

import java.time.LocalDate

data class Assignment(
    val id: String,
    val employeeName: String,
    val employeeEmail: String,
    val projectName: String?,
    val projectStartDate: LocalDate?,
    val projectEndDate: LocalDate?,
)

typealias Assignments = List<Assignment>
