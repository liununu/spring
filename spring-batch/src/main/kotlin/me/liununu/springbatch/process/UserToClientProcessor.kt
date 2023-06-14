package me.liununu.springbatch.process

import me.liununu.springbatch.input.User
import me.liununu.springbatch.output.Client
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor
import java.time.ZoneId

class UserToClientProcessor : ItemProcessor<User, Client> {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private val hkZone = ZoneId.of("Asia/Hong_Kong")

    override fun process(item: User): Client? {
        val id = item.id
        logger.info("Process User($id)...")
        filterId(id) ?: return null
        remarkValidation(item)
        return Client(
            id = id,
            name = "${item.firstName} ${item.lastName}",
            email = item.email,
            gender = Client.Gender.valueOf(item.gender.name),
            remark = item.remark,
            ipAddress = item.ipAddress,
            createdAt = item.createdAt.withZoneSameInstant(hkZone),
        )
    }

    private fun filterId(id: Int): Unit? {
        if (id > 50) {
            logger.info("Filter out User($id)...")
            return null
        }
        return Unit
    }

    private fun remarkValidation(user: User) {
        val remarkLength = user.remark.length
        if (remarkLength > 255) {
            logger.info("Invalid User(${user.id}) with remark length($remarkLength)...")
            throw InvalidRemarkException
        }
    }
}
