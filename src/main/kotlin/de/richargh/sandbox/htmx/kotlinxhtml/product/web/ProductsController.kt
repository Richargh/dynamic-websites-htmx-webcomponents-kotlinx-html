package de.richargh.sandbox.htmx.kotlinxhtml.product.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.AuthPageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.AuthContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.fragment
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.html
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.redirect
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductsFacade
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.PutProduct
import jakarta.validation.Valid
import kotlinx.html.*
import kotlinx.html.consumers.PredicateResult
import kotlinx.html.consumers.filter
import kotlinx.html.stream.appendHTML
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
class ProductsController(
        private val productsFacade: ProductsFacade
) {

    @GetMapping(Paths.Products.INDEX)
    fun getProductsPage(
        @AuthContext ctx: AuthPageContext,): ResponseEntity<String> {
        return html(productsPage(ctx, productsFacade.all()))
    }

    @GetMapping(Paths.Products.ADD)
    fun getAddProductPage(@AuthContext ctx: AuthPageContext) =
            html(putProductPage(ctx, ProductFormData.of(PutProduct.empty()), PutProductType.Add))

    @GetMapping(Paths.Products.EDIT)
    fun getEditProductPage(
        @AuthContext ctx: AuthPageContext,
        @PathVariable("id") rawProductId: String): ResponseEntity<String> {
        val product = productsFacade.byId(ProductId.of(rawProductId))
                ?: return redirect(Paths.Products.INDEX)

        return html(putProductPage(ctx, ProductFormData.of(product), PutProductType.Edit))
    }

    @GetMapping(Paths.Products.SEARCH)
    fun getSearchProductFragment(
        @RequestParam q: String,
        @AuthContext ctx: AuthPageContext): ResponseEntity<String> {
        val products = productsFacade.byQuery(q)

        return fragment(buildString {
            appendHTML().filter { if(it.tagName == "table" || it.tagName == "tbody") PredicateResult.SKIP else PredicateResult.PASS }.table {
                productsTableBody(products)
            }
        })
    }

    @PostMapping(Paths.Products.INDEX)
    fun postProduct(
        @AuthContext ctx: AuthPageContext,
        @Valid @ModelAttribute("productForm") productFormData: ProductFormData, bindingResult: BindingResult): ResponseEntity<String> {
        println(bindingResult)

        if (bindingResult.hasErrors()) {
            val type = if (productsFacade.byId(ProductId.of(productFormData.id)) == null)
                PutProductType.Add else PutProductType.Edit
            return html(putProductPage(ctx, productFormData, type, bindingResult))
        }
        productsFacade.put(productFormData.toDomain())
        return redirect(Paths.Products.INDEX)
    }

}