package me.liununu.configclient

import com.ulisesbocchio.jasyptspringboot.environment.StandardEncryptableEnvironment
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ConfigClientApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder()
        .environment(StandardEncryptableEnvironment())
        .sources(ConfigClientApplication::class.java)
        .run(*args)
}

@RestController
class Controller(private val env: Environment) {

    @GetMapping
    fun greet(): String {
        return env.getProperty("greeting").toString()
    }
}
