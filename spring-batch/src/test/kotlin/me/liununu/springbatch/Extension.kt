package me.liununu.springbatch

import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.file.FlatFileItemReader
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

fun LocalDateTime.toUTCZonedDateTime(): ZonedDateTime = ZonedDateTime.of(this, ZoneId.of("UTC"))

fun <T, R> FlatFileItemReader<T>.use(executionContext: ExecutionContext, block: (FlatFileItemReader<T>) -> R) =
    this.open(executionContext)
        .run { block(this@use) }
        .also { this.close() }
