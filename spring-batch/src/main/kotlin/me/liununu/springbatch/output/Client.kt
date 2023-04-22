package me.liununu.springbatch.output

import java.time.ZonedDateTime

data class Client(
    val id: Int,
    val name: String,
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
