package me.liununu.springbatch.output

import java.time.LocalDateTime

data class Client(
    val id: Int,
    val name: String,
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
