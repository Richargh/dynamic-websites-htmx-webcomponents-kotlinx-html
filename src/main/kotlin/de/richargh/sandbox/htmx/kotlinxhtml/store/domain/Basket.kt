package de.richargh.sandbox.htmx.kotlinxhtml.store.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.UserName
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Product

interface Basket {

    fun all(
        userName: UserName, limit: Int = 10, offset: Int = 0): PagedCollection<BasketItem>

    operator fun get(userName: UserName, basketItemId: BasketItemId): BasketItem?

    fun add(userName: UserName, product: Product)
    fun remove(userName: UserName, basketItemId: BasketItemId)
}