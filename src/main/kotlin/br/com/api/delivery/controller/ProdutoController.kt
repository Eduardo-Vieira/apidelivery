package br.com.api.delivery.controller

import br.com.api.delivery.model.Produto
import br.com.api.delivery.repository.ProdutoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.criteria.CriteriaBuilder

@RestController
@RequestMapping("/produtos")
class ProdutoController(val produtoRepository: ProdutoRepository) {
    // Todos os Produtos
    @GetMapping
    fun findAll(): MutableIterable<Produto> {
        return produtoRepository.findAll()
    }

    // Produto id
    @GetMapping("/{id}")
    fun findById(@PathVariable id:Long): ResponseEntity<Produto>? {
        return produtoRepository.findById(id)
                .map { record -> ResponseEntity.ok().body(record) }
                .orElse(ResponseEntity.notFound().build())
    }

    // Add Produto
    @PostMapping
    fun create(@RequestBody produto: Produto?): ResponseEntity<Produto>? {
        return ResponseEntity.ok().body(produtoRepository.save(produto))
    }

    // update Produto
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id:Long,
               @RequestBody produto: Produto): ResponseEntity<Produto>? {
        return produtoRepository.findById(id)
                .map { record ->
                    record.name = produto.name
                    record.price = produto.price
                    val updated: Produto = produtoRepository.save(record)
                    ResponseEntity.ok().body(updated)
                }
                .orElse(ResponseEntity.notFound().build())
    }

    // delete Produto
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long): ResponseEntity<Produto>? {
        return produtoRepository.findById(id)
                .map { record ->
                    produtoRepository.deleteById(id)
                    ResponseEntity.ok().body(record)
                }
                .orElse(ResponseEntity.notFound().build())
    }
}