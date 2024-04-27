package br.com.nadson.model

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Serdeable
@Entity
data class Client(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        var name: String,
        var documento: String,
        var endereco: String
)
