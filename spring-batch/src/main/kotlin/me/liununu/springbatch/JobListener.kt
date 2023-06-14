package me.liununu.springbatch

import org.slf4j.LoggerFactory
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener

class JobListener : JobExecutionListener {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun beforeJob(jobExecution: JobExecution) {
        logger.info("Execute with parameters: ${jobExecution.jobParameters}")
    }

    override fun afterJob(jobExecution: JobExecution) {
        logger.info("Job done with status: ${jobExecution.exitStatus.exitCode}")
    }
}
