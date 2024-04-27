package br.com.nadson.reporsitory

import br.com.nadson.model.Client
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface ClientRepository: JpaRepository<Client, Long> {
}