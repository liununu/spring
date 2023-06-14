package me.liununu.springbatch

import me.liununu.springbatch.input.User
import me.liununu.springbatch.output.Client
import me.liununu.springbatch.process.InvalidRemarkException
import me.liununu.springbatch.process.UserToClientProcessor
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class BatchConfiguration {

    @Bean
    fun importUserJob(jobRepository: JobRepository, step: Step) =
        JobBuilder("importUserJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .listener(JobListener())
            .flow(step)
            .end()
            .build()

    @Bean
    fun step(
        jobRepository: JobRepository,
        platformTransactionManager: PlatformTransactionManager,
        reader: FlatFileItemReader<User>,
        processor: UserToClientProcessor,
        writer: JpaItemWriter<Client>,
    ) = StepBuilder("step", jobRepository)
        .chunk<User, Client>(10, platformTransactionManager)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .faultTolerant()
        .skip(InvalidRemarkException::class.java)
        .skipLimit(Int.MAX_VALUE)
        .build()
}
