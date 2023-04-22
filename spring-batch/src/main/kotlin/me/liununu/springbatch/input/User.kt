package me.liununu.springbatch.input

import java.time.ZonedDateTime

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: Gender,
    val remark: String,
    val ipAddress: String,
    val createdAt: ZonedDateTime,
) {
    enum class Gender {
        FEMALE,
        MALE,
    }
}
