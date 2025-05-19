package de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web

import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId

object Paths {
    object Greeting {
        const val INDEX = "/greeting"
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
}