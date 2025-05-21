package de.richargh.sandbox.htmx.kotlinxhtml.store.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.AnonContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.AnonPageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.AuthContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.AuthPageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.html
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.fragmentOfMain
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.fragmentOfTbody
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
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
        @AuthContext ctx: AuthPageContext): ResponseEntity<String> {
        return html(basketPage(ctx, storeFacade.allBasketItems(ctx.user.userName)))
    }

    @GetMapping(Paths.Basket.COUNT)
    fun getBasketCountFragment(
        @AnonContext ctx: AnonPageContext): ResponseEntity<String> {
        return fragmentOfMain {
            basketCountFragment(ctx.userData?.basketCount ?: 0)
        }
    }

    @PostMapping(Paths.Basket.ADD)
    fun addToBasket(
        @AuthContext ctx: AuthPageContext,
        @PathVariable("id") rawProductId: String): ResponseEntity<String> {
        val productId = ProductId(rawProductId)
        storeFacade.addToBasket(ctx.user.userName, productId)
        // TODO error handling when item missing
        val item = storeFacade.storeItemById(productId)!!

        return return fragmentOfTbody("HX-Trigger-After-Swap" to Paths.Store.Events.BASKET_CHANGED) {
            storeTableRow(ctx, item)
        }
    }

    @PostMapping(Paths.Basket.REMOVE)
    fun removeFromBasket(
        @AuthContext ctx: AuthPageContext,
        @PathVariable("id") rawBasketItemId: String): ResponseEntity<String> {
        val basketItemId = BasketItemId(rawBasketItemId)
        storeFacade.removeFromBasket(ctx.user.userName, basketItemId)
        return fragmentOfTbody("HX-Trigger-After-Swap" to Paths.Store.Events.BASKET_CHANGED) {

        }
    }

}