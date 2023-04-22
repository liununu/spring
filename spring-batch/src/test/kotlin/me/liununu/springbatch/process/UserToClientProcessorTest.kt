package me.liununu.springbatch.process

import me.liununu.springbatch.input.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneId

class UserToClientProcessorTest {
    @Test
    fun `should map user to client`() {
        val processor = UserToClientProcessor()
        val item = User(
            id = 1,
            firstName = "Lisetta",
            lastName = "Winckles",
            email = "lwinckles0@godaddy.com",
            gender = User.Gender.FEMALE,
            remark = "Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
            ipAddress = "209.92.122.176",
            createdAt = LocalDateTime.of(2022, 6, 20, 16, 18, 8)
        )

        val client = processor.process(item)

        assertThat(client.id).isEqualTo(item.id)
        assertThat(client.name).isEqualTo(item.firstName + " " + item.lastName)
        // ignore the rest fields...
        with(client.createdAt) {
            assertThat(zone).isEqualTo(ZoneId.of("Asia/Hong_Kong"))
            assertThat(year).isEqualTo(2022)
            assertThat(monthValue).isEqualTo(6)
            assertThat(dayOfMonth).isEqualTo(21)
            assertThat(hour).isEqualTo(0)
            assertThat(minute).isEqualTo(18)
            assertThat(second).isEqualTo(8)
        }
    }
}
