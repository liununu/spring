package me.liununu.springbatch

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

fun LocalDateTime.toUTCZonedDateTime(): ZonedDateTime = ZonedDateTime.of(this, ZoneId.of("UTC"))
