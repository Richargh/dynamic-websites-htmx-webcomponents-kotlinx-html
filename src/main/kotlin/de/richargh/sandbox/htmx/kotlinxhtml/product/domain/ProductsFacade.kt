package de.richargh.sandbox.htmx.kotlinxhtml.product.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.money.domain.Euro
import java.util.concurrent.ConcurrentHashMap


class ProductsFacade {
    private val allProducts = ConcurrentHashMap<ProductId, Product>()

    init {
        sequenceOf(
                Product(ProductId.unique(), "Cup", Euro.ofEuros(1), 1),
                Product(ProductId.unique(), "Bottle", Euro.ofEuros(10), 2)
        )
                .associateByTo(allProducts, Product::id)
    }

    fun all(limit: Int = 10, offset: Int = 0): PagedCollection<Product> {
        return byQuery("", limit, offset)
    }

    fun byId(productId: ProductId): Product? {
        return allProducts[productId]
    }

    fun byQuery(q: String, limit: Int = 10, offset: Int = 0): PagedCollection<Product> {
        var total = 0
        val collection = allProducts.asSequence()
            .filter { it.value.name.contains(q, ignoreCase = true) }
            .filterIndexed { index, _ ->
                total++

                index in offset until limit + offset
            }
            .map { it.value }
            .toList()

        return PagedCollection(limit, offset, total, collection)
    }

    fun put(putProduct: PutProduct) {
        var product = allProducts[putProduct.id]
        product = product?.merge(putProduct) ?: Product.of(putProduct)
        allProducts[product.id] = product
    }
}

