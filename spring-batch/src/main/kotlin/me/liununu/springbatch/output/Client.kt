package me.liununu.springbatch.output

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.ZonedDateTime

@Entity
data class Client(
    @Id val id: Int,
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
