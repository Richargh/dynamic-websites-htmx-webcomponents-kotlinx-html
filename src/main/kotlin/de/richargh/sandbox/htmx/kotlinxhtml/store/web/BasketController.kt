package de.richargh.sandbox.htmx.kotlinxhtml.store.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.Context
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.html
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.redirect
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Product
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.BasketItemId
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.StoreFacade
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class BasketController(
        private val storeFacade: StoreFacade
) {

    @GetMapping(Paths.Basket.INDEX)
    fun getBasketPage(
        @Context ctx: PageContext,): ResponseEntity<String> {
        // TODO user should always exist because we require a login
        return html(basketPage(ctx, storeFacade.allBasketItems(ctx.user!!.userName)))
    }

    @PostMapping(Paths.Basket.ADD)
    fun addToBasket(
        @Context ctx: PageContext,
        @PathVariable("id") rawProductId: String): ResponseEntity<String> {
        // TODO user should always exist because we require a login
        storeFacade.addToBasket(ctx.user!!.userName, ProductId(rawProductId))
        return return redirect(Paths.Store.INDEX)
    }

    @PostMapping(Paths.Basket.REMOVE)
    fun removeFromBasket(
        @Context ctx: PageContext,
        @PathVariable("id") rawBasketItemId: String): ResponseEntity<String> {
        // TODO user should always exist because we require a login
        storeFacade.removeFromBasket(ctx.user!!.userName, BasketItemId(rawBasketItemId))
        return return redirect(Paths.Basket.INDEX)
    }

}