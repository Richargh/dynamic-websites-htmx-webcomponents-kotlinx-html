package de.richargh.sandbox.htmx.kotlinxhtml.store.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.money.domain.Euro
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Product
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.PutProduct
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty


data class ItemFormData(
        @field:NotEmpty
        val id: String,
        @field:NotEmpty
        val name: String,
        @field:Min(1)
        val priceInCents: Int) {
    fun toDomain(): PutProduct {
        return PutProduct(
                ProductId.of(id),
                name,
                Euro.ofCents(priceInCents)
        )
    }

    companion object {
        fun of(putProduct: PutProduct): ItemFormData {
            return ItemFormData(
                    putProduct.id.rawValue,
                    putProduct.name,
                    putProduct.price.rawCents
            )
        }

        fun of(product: Product): ItemFormData {
            return ItemFormData(
                    product.id.rawValue,
                    product.name,
                    product.price.rawCents
            )
        }
    }
}

