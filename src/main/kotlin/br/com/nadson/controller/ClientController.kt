package br.com.nadson.controller

import br.com.nadson.model.Client
import br.com.nadson.reporsitory.ClientRepository
import br.com.nadson.service.ClientService
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.QueryValue
import jakarta.transaction.Transactional

@Controller("/clients")
class ClientController (
        private val clientService: ClientService) {

    @Get
    fun findAll(@QueryValue name: String?, pageable: Pageable): Page<Client> {
        return clientService.findAll(name, pageable)
    }

    @Get("/listClient")
    fun listClient(@QueryValue search_name: String?): List<Client> {
        return clientService.list(search_name)
    }

    @Post
    fun create(@Body client: Client): HttpResponse<Client> {
        val clientDB = clientService.create(client)
        return  HttpResponse.created(clientDB)
    }

    @Get("/{id}")
    fun findById(@PathVariable id: Long): Client {
        return clientService.findById(id)
    }

    @Delete("/{id}")
    fun delete(@PathVariable id: Long): HttpResponse<Unit>{
        clientService.delete(id)
        return HttpResponse.noContent()
    }

    @Put("/{id}")
    fun update(@PathVariable id: Long, @Body client: Client) {
        clientService.update(id, client)
    }
}