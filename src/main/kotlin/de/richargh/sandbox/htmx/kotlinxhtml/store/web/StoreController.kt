package de.richargh.sandbox.htmx.kotlinxhtml.store.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.Context
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.fragment
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.html
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.StoreFacade
import kotlinx.html.*
import kotlinx.html.consumers.PredicateResult
import kotlinx.html.consumers.filter
import kotlinx.html.stream.appendHTML
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class StoreController(
        private val storeFacade: StoreFacade
) {

    @GetMapping(Paths.Store.INDEX)
    fun getStorePage(
        @Context ctx: PageContext,): ResponseEntity<String> {
        return html(storePage(ctx, storeFacade.queryStoreItems("")))
    }


    @GetMapping(Paths.Store.SEARCH)
    fun getSearchStoreFragment(
        @RequestParam q: String,
        @Context ctx: PageContext): ResponseEntity<String> {
        val products = storeFacade.queryStoreItems(q)

        return fragment(buildString {
            appendHTML().filter { if(it.tagName == "table" || it.tagName == "tbody") PredicateResult.SKIP else PredicateResult.PASS }.table {
                storeTableBody(ctx, products)
            }
        })
    }

}