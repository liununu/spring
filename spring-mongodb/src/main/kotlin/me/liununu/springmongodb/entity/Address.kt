package me.liununu.springmongodb.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("addresses")
data class Address(
    @Id val id: String,
    val pid: String,
    val aid: String,
    val country: String,
    val street: String,
    val postalCode: String?,
)
