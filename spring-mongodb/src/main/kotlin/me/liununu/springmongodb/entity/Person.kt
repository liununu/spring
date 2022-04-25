package me.liununu.springmongodb.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Person(
    @Id val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
)
