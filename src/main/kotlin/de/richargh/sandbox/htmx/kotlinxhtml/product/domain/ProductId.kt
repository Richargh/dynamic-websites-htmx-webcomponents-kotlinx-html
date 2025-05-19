package de.richargh.sandbox.htmx.kotlinxhtml.product.domain

import java.util.*


data class ProductId(val rawValue: String) {
    override fun toString(): String {
        return rawValue
    }

    companion object {
        fun unique(): ProductId {
            return ProductId(UUID.randomUUID().toString())
        }

        fun of(rawValue: String): ProductId {
            return ProductId(rawValue)
        }
    }
}
