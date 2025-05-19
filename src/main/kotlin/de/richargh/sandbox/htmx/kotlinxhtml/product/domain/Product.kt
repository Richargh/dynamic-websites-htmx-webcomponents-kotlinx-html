package de.richargh.sandbox.htmx.kotlinxhtml.product.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.money.domain.Euro
import java.security.InvalidParameterException

data class Product(val id: ProductId, val name: String, val price: Euro, val stock: Int) {
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
