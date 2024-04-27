package br.com.nadson.controller

import br.com.nadson.model.Client
import br.com.nadson.reporsitory.ClientRepository
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

@Controller("/clients")
class ClientController (private val clientRepository: ClientRepository) {

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
}