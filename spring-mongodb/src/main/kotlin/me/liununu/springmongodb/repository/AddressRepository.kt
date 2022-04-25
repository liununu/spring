package me.liununu.springmongodb.repository

import me.liununu.springmongodb.entity.Address
import org.springframework.data.mongodb.repository.MongoRepository

interface AddressRepository : MongoRepository<Address, String> {
    fun findAllByCountry(country: String): List<Address>
}
