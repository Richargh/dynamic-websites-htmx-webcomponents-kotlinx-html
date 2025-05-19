package de.richargh.sandbox.htmx.kotlinxhtml.store.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.BasketItem
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.fragments.web.generalPage
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import kotlinx.html.*

fun basketPage(ctx: PageContext, items: PagedCollection<BasketItem>) = generalPage(ctx) {
    h1 { +"Basket" }

    basketTable(items)
}

fun FlowContent.basketTable(items: Collection<BasketItem>) = table {
    attributes["data-testid"] = "basket-table"
    thead {
        tr {
            th { +"Name" }
            th { +"Price" }
            th { }
        }
    }
    basketTableBody(items)
}

private const val productsTableBodyId = "search-results"

fun TABLE.basketTableBody(items: Collection<BasketItem>) = tbody {
    id = productsTableBodyId
    items.forEach {
        tr {
            td { +it.product.name }
            td { +it.product.price.toString() }
            td { a(href = Paths.Basket.remove(it.id)) { +"Remove" } }
        }
    }
}
