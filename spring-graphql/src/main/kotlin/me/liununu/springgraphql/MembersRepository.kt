package me.liununu.springgraphql

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Repository

@Repository
class MembersRepository(
    objectMapper: ObjectMapper,
    @Value("classpath:members.json") private val dummyResource: Resource
) {
    private val data = objectMapper.readValue<List<Member>>(dummyResource.file)

    fun findAll(): List<Member> = this.data
}
