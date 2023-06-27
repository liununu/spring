package me.liununu.springdata.entity

import jakarta.persistence.*

@Entity
@Table(name = "employees")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    val name: String,
    val email: String,
    val projectName: String?,
    val active: Boolean,
)
