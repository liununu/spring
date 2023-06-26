package me.liununu.springdata.query

data class Assignment(
    val id: String,
    val employeeName: String,
    val employeeEmail: String,
    val projectName: String?,
    val projectStartDate: String?,
    val projectEndDate: String?,
)

typealias Assignments = List<Assignment>
