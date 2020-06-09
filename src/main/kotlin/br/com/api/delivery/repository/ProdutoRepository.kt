package br.com.api.delivery.repository

import br.com.api.delivery.model.Produto
import org.springframework.data.jpa.repository.JpaRepository

interface ProdutoRepository: JpaRepository<Produto, Long> {
    fun save(produto: Produto?): Produto
}