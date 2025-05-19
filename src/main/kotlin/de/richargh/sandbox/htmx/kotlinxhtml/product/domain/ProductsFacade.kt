package de.richargh.sandbox.htmx.kotlinxhtml.product.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection

class ProductsFacade(
    private val allProducts: Products
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
    }
}
