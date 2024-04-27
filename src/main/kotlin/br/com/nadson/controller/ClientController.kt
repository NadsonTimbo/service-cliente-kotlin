package br.com.nadson.controller

import br.com.nadson.model.Client
import br.com.nadson.reporsitory.ClientRepository
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import jakarta.transaction.Transactional

@Controller("/clients")
open class ClientController (private val clientRepository: ClientRepository) {

    @Get
    fun findAll(): List<Client> {
        return clientRepository.findAll()
    }

    @Post
    fun create(@Body client: Client) {
        clientRepository.save(client)
    }

    @Get("/{id}")
    fun findById(@PathVariable id: Long): Client {
        return clientRepository.findById(id).get()
    }

    @Delete
    fun delete(@PathVariable id: Long){
        clientRepository.deleteById(id)
    }

    @Put("/{id}")
    @Transactional
    open fun update(@PathVariable id: Long, @Body client: Client) {
        val clientDb = clientRepository.findById(id).get()
        clientDb.name = client.name
        clientDb.documento = client.documento
        clientDb.endereco = client.endereco

        clientRepository.save(clientDb)
    }
}