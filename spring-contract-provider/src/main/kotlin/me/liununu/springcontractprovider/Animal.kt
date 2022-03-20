package me.liununu.springcontractprovider

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class Animal(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val animal: String,
    val age: Int,
    val availableFrom: String,
    val gender: String,
    val location: Location,
    val eligibility: Eligibility,
    val interests: List<String>,
)

@JsonNaming(SnakeCaseStrategy::class)
data class Eligibility(
    val available: Boolean,
    val previouslyMarried: Boolean,
)

@JsonNaming(SnakeCaseStrategy::class)
data class Location(
    val description: String,
    val country: String,
    val postCode: Int,
)
