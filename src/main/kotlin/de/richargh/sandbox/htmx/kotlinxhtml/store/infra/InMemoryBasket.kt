package de.richargh.sandbox.htmx.kotlinxhtml.store.infra

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.UserName
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Product
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.Basket
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.BasketItem
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.BasketItemId
import java.util.concurrent.ConcurrentHashMap

class InMemoryBasket: Basket {

    private val productsInBasketOfUser = ConcurrentHashMap<UserName, MutableMap<BasketItemId, BasketItem>>()

    override fun all(
        userName: UserName,
        limit: Int,
        offset: Int
    ): PagedCollection<BasketItem> {
        val productsInBasket = productsInBasketOfUser[userName]
        if(productsInBasket == null)
            return PagedCollection.empty()

        var total = 0
        val collection = productsInBasket.asSequence()
            .filterIndexed { index, _ ->
                total++

                index in offset until limit + offset
            }
            .map { it.value }
            .toList()

        return PagedCollection(limit, offset, total, collection)
    }

    override fun get(userName: UserName, basketItemId: BasketItemId): BasketItem? {
        return productsInBasketOfUser[userName]?.get(basketItemId)
    }

    override fun add(
        userName: UserName,
        product: Product
    ) {
        productsInBasketOfUser.compute(userName) { key, value ->
            val products = value ?: mutableMapOf()
            val basketItem = BasketItem(BasketItemId.unique(), product)
            products[basketItem.id] = basketItem

            products
        }
    }

    override fun remove(
        userName: UserName,
        basketItemId: BasketItemId
    ) {
        productsInBasketOfUser.compute(userName) { key, products ->
            if(products == null)
                return@compute null

            products.remove(basketItemId)

            products
        }
    }
}