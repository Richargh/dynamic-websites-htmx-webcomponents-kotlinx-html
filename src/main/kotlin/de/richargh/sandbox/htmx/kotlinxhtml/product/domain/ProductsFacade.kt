package de.richargh.sandbox.htmx.kotlinxhtml.product.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.money.domain.Euro
import java.util.concurrent.ConcurrentHashMap


class ProductsFacade {
    private val allProducts = ConcurrentHashMap<ProductId, Product>()

    init {
        demoData
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

private val demoData = sequenceOf(
    Product(ProductId.unique(), "Cup", Euro.ofEuros(1), 1),
    Product(ProductId.unique(), "Car", Euro.ofEuros(20_000), 2),
    Product(ProductId.unique(), "Mac", Euro.ofEuros(2_000), 3),
    Product(ProductId.unique(), "Tractor", Euro.ofEuros(100_000), 4),
    Product(ProductId.unique(), "Desk", Euro.ofEuros(500), 5),
    Product(ProductId.unique(), "Flask", Euro.ofEuros(2), 6),
    Product(ProductId.unique(), "Screen", Euro.ofEuros(600), 7),
    Product(ProductId.unique(), "USB-C Cable", Euro.ofEuros(50), 8),
    Product(ProductId.unique(), "USB-A Cable", Euro.ofEuros(5), 9),
    Product(ProductId.unique(), "Phone", Euro.ofEuros(1_200), 10),
) + generateSequence(1) { it + 1 }.take(1000).map {
    Product(ProductId.unique(), "Bottle worth $it EUR", Euro.ofEuros(it), 1000/it)
}
