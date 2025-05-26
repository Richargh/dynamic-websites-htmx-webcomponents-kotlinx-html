package de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web

import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.BasketItemId
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId

object Paths {
    object Greeting {
        const val INDEX = "/greeting"
        const val OPEN_MODAL = "$INDEX/open"
        const val SUBMIT_MODAL = "$INDEX/submit"
    }

    object Login {
        const val INDEX = "/login"
    }

    object Logout {
        const val INDEX = "/logout"
    }

    object Products {
        const val INDEX = "/products"
        const val SEARCH = "$INDEX/search"
        const val ADD = "$INDEX/add"
        const val EDIT = "$INDEX/{id}"
        fun edit(id: ProductId) = "$INDEX/${id.rawValue}"
    }

    object Store {
        private const val name = "store"
        const val INDEX = "/$name"
        const val SEARCH = "$INDEX/search"
        object Events {
            const val BASKET_CHANGED = "$name-basket-changed"
        }
    }

    object Basket {
        const val INDEX = "/basket"
        const val COUNT = "/basket/count"
        const val ADD = "$INDEX/{id}/add"
        fun add(id: ProductId) = "$INDEX/${id.rawValue}/add"
        const val REMOVE = "$INDEX/{id}/remove"
        fun remove(id: BasketItemId) = "$INDEX/${id.rawValue}/remove"
    }

    object Widgets {
        const val INDEX = "/dynamic/app/html-web-components"
        const val GET_ONE = "$INDEX/{jsFileName}"
        fun getOne(rawJsFilePath: String) = "${INDEX}/${rawJsFilePath}"
    }
}