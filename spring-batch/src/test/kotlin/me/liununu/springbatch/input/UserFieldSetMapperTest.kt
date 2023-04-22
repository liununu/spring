package me.liununu.springbatch.input

import me.liununu.springbatch.toUTCZonedDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory
import java.time.LocalDateTime

class UserFieldSetMapperTest {
    @Test
    fun `should map field set to user`() {
        val mapper = UserFieldSetMapper()
        val fieldSet = DefaultFieldSetFactory().create(
            arrayOf(
                "1",
                "Lisetta",
                "Winckles",
                "lwinckles0@godaddy.com",
                "Female",
                "Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
                "209.92.122.176",
                "2022-06-20T16:18:08Z",
            ),
            arrayOf(
                "id",
                "first_name",
                "last_name",
                "email",
                "gender",
                "remark",
                "ip_address",
                "created_at"
            )
        )

        val user = mapper.mapFieldSet(fieldSet)

        assertThat(user.id).isEqualTo(1)
        assertThat(user.email).isEqualTo("lwinckles0@godaddy.com")
        assertThat(user.gender).isEqualTo(User.Gender.FEMALE)
        assertThat(user.createdAt).isEqualTo(
            LocalDateTime.of(2022, 6, 20, 16, 18, 8).toUTCZonedDateTime()
        )
    }
}
