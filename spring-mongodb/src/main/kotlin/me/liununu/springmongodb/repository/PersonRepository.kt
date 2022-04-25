package me.liununu.springmongodb.repository

import me.liununu.springmongodb.entity.Person
import org.springframework.data.mongodb.repository.MongoRepository

interface PersonRepository : MongoRepository<Person, String>
