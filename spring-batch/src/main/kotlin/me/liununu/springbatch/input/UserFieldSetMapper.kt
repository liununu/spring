package me.liununu.springbatch.input

import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FieldSet
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ISO_INSTANT

class UserFieldSetMapper : FieldSetMapper<User> {

    override fun mapFieldSet(fieldSet: FieldSet) =
        with(fieldSet) {
            User(
                readInt("id"),
                readString("first_name"),
                readString("last_name"),
                readString("email"),
                User.Gender.valueOf(readString("gender").uppercase()),
                readString("remark"),
                readString("ip_address"),
                ZonedDateTime.ofInstant(
                    Instant.from(ISO_INSTANT.parse(readString("created_at"))),
                    ZoneId.of("UTC")
                )
            )
        }
}
