package de.richargh.sandbox.htmx.kotlinxhtml.store.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.repo.domain.Entity
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Product
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId

data class Item(override val id: ProductId, val product: Product, val stock: Int): Entity<ProductId> {

    override fun matchesQuery(query: String) = product.name.contains(query, ignoreCase = true)

    companion object {
        fun of(product: Product): Item {
            return Item(
                product.id,
                product,
                0
            )
        }
    }
}
