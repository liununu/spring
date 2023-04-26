package me.liununu.springbatch.input

import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class ReaderConfiguration {

    @Bean
    fun reader(): FlatFileItemReader<User> {
        val tokenizer = DelimitedLineTokenizer()
        return FlatFileItemReaderBuilder<User>()
            .name("user-reader")
            .resource(ClassPathResource("MOCK_DATA.csv"))
            .recordSeparatorPolicy(DefaultRecordSeparatorPolicy())
            .linesToSkip(1)
            .skippedLinesCallback { tokenizer.setNames(*it.split(",").toTypedArray()) }
            .lineTokenizer(tokenizer)
            .fieldSetMapper(UserFieldSetMapper())
            .build()
    }
}
