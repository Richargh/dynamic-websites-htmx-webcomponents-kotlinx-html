package de.richargh.sandbox.htmx.kotlinxhtml.store.domain

import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Product

data class BasketItem(val id: BasketItemId, val product: Product) {

}
