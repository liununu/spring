package me.liununu.springmongodb.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("persons")
data class Person(
    @Id val id: String,
    val pid: String,
    val firstName: String,
    val lastName: String,
    val email: String,
)
