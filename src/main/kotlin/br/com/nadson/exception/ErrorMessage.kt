package br.com.nadson.exception

import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDateTime

@Serdeable
data class ErrorMessage(
        val dataHora: String = LocalDateTime.now().toString(),
        val msg: String?
)
