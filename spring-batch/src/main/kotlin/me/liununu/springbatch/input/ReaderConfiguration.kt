package me.liununu.springbatch.input

import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class ReaderConfiguration {

    @Bean
    fun reader() = FlatFileItemReaderBuilder<User>()
        .name("user-reader")
        .resource(ClassPathResource("MOCK_DATA.csv"))
        .lineTokenizer(DelimitedLineTokenizer().apply {
            setNames(
                "id", "first_name", "last_name", "email", "gender", "remark", "ip_address", "created_at"
            )
        })
        .linesToSkip(1)
        .fieldSetMapper(UserFieldSetMapper())
        .build()
}
