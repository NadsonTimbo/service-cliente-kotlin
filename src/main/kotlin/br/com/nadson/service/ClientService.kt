package br.com.nadson.service

import br.com.nadson.exception.RegisterNotFoundException
import br.com.nadson.model.Client
import br.com.nadson.reporsitory.ClientRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.inject.Singleton
import jakarta.transaction.Transactional

@Singleton
open class ClientService (
        private val clientRepository: ClientRepository
) {

    fun findAll(name: String?, pageable: Pageable): Page<Client> {
        var clientes = if (name == null) {
            clientRepository.findAll(pageable)
        }else{
            clientRepository.findByName(name, pageable)
        }
        return clientes
    }

    @Transactional
    open fun list(search_name: String?): List<Client> {
        return clientRepository.listPersonalization(search_name)
    }

    fun create(client: Client): Client {
        return clientRepository.save(client)
    }

    fun findById(id: Long): Client {
        return clientRepository.findById(id).orElseThrow{
            RegisterNotFoundException("Cliente não encontrado")
        }
    }

    fun delete(id: Long){
        val clientDB = findById(id)
        clientRepository.delete(clientDB)
    }

    @Transactional
    open fun update(id: Long, client: Client) {
        val clientDb = findById(id)
        clientDb.name = client.name
        clientDb.documento = client.documento
        clientDb.endereco = client.endereco

        clientRepository.save(clientDb)
    }

}