package de.richargh.sandbox.htmx.kotlinxhtml.product.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.money.domain.Euro
import de.richargh.sandbox.htmx.kotlinxhtml.commons.repo.domain.Entity
import java.security.InvalidParameterException

data class Product(override val id: ProductId, val name: String, val price: Euro, val stock: Int): Entity<ProductId> {

    override fun matchesQuery(query: String): Boolean {
        return name.contains(query, ignoreCase = true)
    }

    fun merge(putProduct: PutProduct): Product {
        if (putProduct.id != id) {
            throw InvalidParameterException("Product id " + id + " does not match merge id " + putProduct.id)
        }
        return Product(
                id,
                putProduct.name,
                putProduct.price,
                stock
        )
    }

    companion object {
        fun of(putProduct: PutProduct): Product {
            return Product(
                    putProduct.id,
                    putProduct.name,
                    putProduct.price,
                    0
            )
        }
    }
}
