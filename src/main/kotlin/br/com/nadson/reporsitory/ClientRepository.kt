package br.com.nadson.reporsitory

import br.com.nadson.model.Client
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.persistence.EntityManager

@Repository
abstract class ClientRepository(
        private val entityManager: EntityManager
): JpaRepository<Client, Long> {

    abstract fun findByName(name: String, pageable: Pageable): Page<Client>

    @Query("select c from Client c")
    abstract fun list(): List<Client>

    fun listPersonalization(search_name: String?): List<Client> {
        var queryString = "Select c from Client c "
        if (search_name != null)
            queryString += "where c.name like CONCAT('%', :name, '%')"

        var query = entityManager.createQuery(queryString)

        if (search_name != null)
            query.setParameter("name", search_name)

        var clients = query.resultList
        return clients as List<Client>

    }
}