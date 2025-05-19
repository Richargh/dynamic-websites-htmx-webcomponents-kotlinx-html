package de.richargh.sandbox.htmx.kotlinxhtml.store.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.fragments.web.generalPage
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.Item
import kotlinx.html.*

fun storePage(ctx: PageContext, items: PagedCollection<Item>) = generalPage(ctx) {
    h1 { +"Store" }

    storeSearch()
    storeTable(ctx, items)
    storePagination(items)
}

@HtmlTagMarker
fun FlowContent.storeSearch() = input {
    type = InputType.search
    name = "q"
    placeholder = "Begin Typing To Search the Store..."

    attributes["hx-get"] = Paths.Products.SEARCH
    attributes["hx-trigger"] = "keyup changed delay:500ms, search"
    attributes["hx-target"] = "#$productsTableBodyId"
}

fun FlowContent.storeTable(ctx: PageContext, items: Collection<Item>) = table {
    attributes["data-testid"] = "store-table"
    thead {
        tr {
            th { +"Name" }
            th { +"Price" }
            th { +"Stock" }
            th { }
        }
    }
    storeTableBody(ctx, items)
}

private const val productsTableBodyId = "search-results"

fun TABLE.storeTableBody(ctx: PageContext, items: Collection<Item>) = tbody {
    id = productsTableBodyId
    items.forEach {
        tr {
            td { +it.product.name }
            td { +it.product.price.toString() }
            td { +it.stock.toString() }
            td {
                form {
                    action = Paths.Basket.add(it.id)
                    method = FormMethod.post
                    button(type = ButtonType.submit, classes = "secondary") {
                        value = "AddToBasket"
                        +"Add to Basket"
                    }
                    input(type = InputType.hidden, name = "_csrf") { value = ctx.csrfToken!!.rawValue }
                }
            }
        }
    }
}

fun MAIN.storePagination(items: PagedCollection<Item>) = div(classes = "pagination") {
    button(classes = "pagination-arrow disabled") {
        a {
            +"<"
        }
    }
    (1..items.pageCount).forEach {
        when (it) {
            1 -> button(classes = "pagination-item active") {
            a {
                +it.toString()
            }
            }
            in 2..5 -> button(classes = "pagination-item") {
//            a {
                +it.toString()
//            }
            }
        }
    }
    button(classes = "pagination-item") {
        //            a {
        +"..."
        //            }
    }
    button(classes = "pagination-item") {
    //            a {
        +(items.pageCount - 1).toString()
    //            }
    }
    button(classes = "pagination-arrow disabled") {
        a {
            +">"
        }
    }
}