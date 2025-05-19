package de.richargh.sandbox.htmx.kotlinxhtml.product.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.Item
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.Stock

class ProductsFacade(
    private val allProducts: Products,
    private val allStock: Stock
) {

    fun all(limit: Int = 10, offset: Int = 0): PagedCollection<Product> {
        return byQuery("", limit, offset)
    }

    fun byId(productId: ProductId): Product? {
        return allProducts[productId]
    }

    fun byQuery(q: String, limit: Int = 10, offset: Int = 0): PagedCollection<Product> {
        return allProducts.byQuery(q, limit, offset)
    }

    fun put(putProduct: PutProduct) {
        var product = allProducts[putProduct.id]
        product = product?.merge(putProduct) ?: Product.of(putProduct)
        allProducts[product.id] = product

        // TODO: model this nicely with events and not direct DB-access
        allStock[product.id] = allStock[product.id]?.copy(product = product) ?: Item.of(product)
    }
}
