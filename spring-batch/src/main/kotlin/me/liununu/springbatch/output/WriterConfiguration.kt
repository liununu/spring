package me.liununu.springbatch.output

import jakarta.persistence.EntityManagerFactory
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WriterConfiguration {

    @Bean
    fun writer(entityManagerFactory: EntityManagerFactory) = JpaItemWriterBuilder<Client>()
        .entityManagerFactory(entityManagerFactory)
        .build()
}
