package br.com.api.delivery.model

import javax.persistence.*

@Entity
data class Produto (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column val id: Long? = 0,
        @Column var name:String,
        @Column var price:Double
)