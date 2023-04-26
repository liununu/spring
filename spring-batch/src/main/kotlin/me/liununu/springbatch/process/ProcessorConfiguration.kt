package me.liununu.springbatch.process

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProcessorConfiguration {

    @Bean
    fun processor() = UserToClientProcessor()
}
