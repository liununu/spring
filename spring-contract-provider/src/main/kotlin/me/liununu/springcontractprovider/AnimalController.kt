package me.liununu.springcontractprovider

import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

private const val ANIMALS_URL = "http://localhost:8979/animals"

@RestController
@RequestMapping(value = ["/animals"], produces = ["application/json;charset=utf-8"])
class AnimalController(private val restTemplate: RestTemplate) {

    @GetMapping("/available")
    fun findAll(@RequestHeader(value = AUTHORIZATION, required = false) authorization: String?) =
        authorizedExecution(authorization) {
            restTemplate.getForObject(ANIMALS_URL, Array<Animal>::class.java)
                .orEmpty()
                .filter { it.eligibility.available }
        }

    @GetMapping("/{id}")
    fun findById(
        @RequestHeader(value = AUTHORIZATION, required = false) authorization: String?,
        @PathVariable id: Int,
    ) = authorizedExecution(authorization) {
        restTemplate.getForObject(ANIMALS_URL, Array<Animal>::class.java)
            .orEmpty()
            .find { it.id == id }
    }
        .body
        ?.let { ResponseEntity.ok(it) }
        ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(
        @RequestHeader(value = AUTHORIZATION, required = false) authorization: String?,
        @RequestBody animal: Animal,
    ) = restTemplate.postForObject(ANIMALS_URL, animal, Animal::class.java)
        ?.let { ResponseEntity.ok(it) }
        ?: ResponseEntity.badRequest().build()

    // Simply verify the authorization header
    private fun <T> authorizedExecution(authorization: String?, executor: () -> T): ResponseEntity<T> {
        if (authorization.isNullOrBlank()) {
            return ResponseEntity.status(UNAUTHORIZED).build()
        }
        return ResponseEntity.ok(executor.invoke())
    }
}
