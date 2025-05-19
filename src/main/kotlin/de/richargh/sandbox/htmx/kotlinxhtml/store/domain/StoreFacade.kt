package de.richargh.sandbox.htmx.kotlinxhtml.store.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.UserName
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId


class StoreFacade(
    private val stock: Stock,
    private val basket: Basket
) {

    fun queryStoreItems(query: String, limit: Int = 10, offset: Int = 0): PagedCollection<Item> {
        return stock.byQuery(query, limit, offset)
    }

    fun allBasketItems(userName: UserName): PagedCollection<BasketItem> {
        return basket.all(userName)
    }

    fun addToBasket(userName: UserName, productId: ProductId){
        val item = stock[productId]
            ?: throw IllegalArgumentException("Item $productId does not exist or ist not in Stock")
        basket.add(userName, item.product)
    }

    fun removeFromBasket(userName: UserName, basketItemId: BasketItemId){
        basket.remove(userName, basketItemId)
    }
}
