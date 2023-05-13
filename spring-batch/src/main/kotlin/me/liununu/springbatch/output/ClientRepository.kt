package me.liununu.springbatch.output

import org.springframework.data.repository.CrudRepository

interface ClientRepository : CrudRepository<Client, Int>
