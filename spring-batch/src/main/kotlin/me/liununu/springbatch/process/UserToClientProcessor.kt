package me.liununu.springbatch.process

import me.liununu.springbatch.input.User
import me.liununu.springbatch.output.Client
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor
import java.time.ZoneId

class UserToClientProcessor : ItemProcessor<User, Client> {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private val hkZone = ZoneId.of("Asia/Hong_Kong")

    override fun process(item: User) =
        with(item) {
            logger.info("Process User($id)...")
            Client(
                id = id,
                name = "$firstName $lastName",
                email = email,
                gender = Client.Gender.valueOf(gender.name),
                remark = remark,
                ipAddress = ipAddress,
                createdAt = createdAt.withZoneSameInstant(hkZone),
            )
        }
}
