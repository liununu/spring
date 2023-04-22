package me.liununu.springbatch.input

import java.time.LocalDateTime

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: Gender,
    val remark: String,
    val ipAddress: String,
    val createdAt: LocalDateTime,
) {
    enum class Gender {
        FEMALE,
        MALE,
    }
}
