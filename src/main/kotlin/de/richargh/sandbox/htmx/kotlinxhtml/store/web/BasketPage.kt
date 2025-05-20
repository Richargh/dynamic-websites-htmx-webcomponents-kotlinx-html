package de.richargh.sandbox.htmx.kotlinxhtml.store.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.fragments.web.generalPage
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.BasketItem
import kotlinx.html.*

fun basketPage(ctx: PageContext, items: PagedCollection<BasketItem>) = generalPage(ctx) {
    h1 { +"Basket" }

    basketTable(ctx, items)
}

fun FlowContent.basketCountFragment(basketCount: Int) = a(href = Paths.Basket.INDEX) {
    attributes["hx-get"] = Paths.Basket.COUNT
    attributes["hx-trigger"] = "${Paths.Store.Events.BASKET_CHANGED} from:body"
    attributes["hx-swap"] = "outerHTML"

    +"Basket ($basketCount)"
}

fun FlowContent.basketTable(ctx: PageContext, items: Collection<BasketItem>) = table {
    attributes["data-testid"] = "basket-table"
    thead {
        tr {
            th { +"Name" }
            th { +"Price" }
            th { }
        }
    }
    basketTableBody(ctx, items)
}

private const val productsTableBodyId = "search-results"

fun TABLE.basketTableBody(ctx: PageContext, items: Collection<BasketItem>) = tbody {
    id = productsTableBodyId
    items.forEach {
        basketTableRow(ctx, it)
    }
}

fun TBODY.basketTableRow(ctx: PageContext, item: BasketItem) = tr {
    val rowId = "basket-item-${item.id}"
    id = rowId
    td { +item.product.name }
    td { +item.product.price.toString() }
    td {
        form {
            action = Paths.Basket.remove(item.id)
            method = FormMethod.post
            button(type = ButtonType.submit, classes = "secondary") {
                value = "RemoveFromBasket"
                attributes["hx-post"] = Paths.Basket.remove(item.id)
                attributes["hx-target"] = "#$rowId"
                attributes["hx-swap"] = "outerHTML"
                +"Remove"
            }
            input(type = InputType.hidden, name = "_csrf") { value = ctx.csrfToken!!.rawValue }
        }
    }
}
