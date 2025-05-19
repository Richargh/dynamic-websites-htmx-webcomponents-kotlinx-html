package de.richargh.sandbox.htmx.kotlinxhtml.store.domain

import java.util.*


data class BasketItemId(val rawValue: String) {
    override fun toString(): String {
        return rawValue
    }

    companion object {
        fun unique(): BasketItemId {
            return BasketItemId(UUID.randomUUID().toString())
        }

        fun of(rawValue: String): BasketItemId {
            return BasketItemId(rawValue)
        }
    }
}
