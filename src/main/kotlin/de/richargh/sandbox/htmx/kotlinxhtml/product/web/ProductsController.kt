package de.richargh.sandbox.htmx.kotlinxhtml.product.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.Context
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.money.domain.Euro
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.fragment
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.html
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.redirect
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Product
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductsFacade
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.PutProduct
import jakarta.validation.Valid
import kotlinx.html.*
import kotlinx.html.consumers.PredicateResult
import kotlinx.html.consumers.filter
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.stream.appendHTML
import kotlinx.html.stream.createHTML
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.NativeWebRequest

@Controller
class ProductsController(
        private val productsFacade: ProductsFacade
) {

    @GetMapping(Paths.Products.INDEX)
    fun getProductsPage(
        @Context ctx: PageContext,): ResponseEntity<String> {
        return html(productsPage(ctx, productsFacade.all()))
    }

    @GetMapping(Paths.Products.ADD)
    fun getAddProductPage(@Context ctx: PageContext) =
            html(putProductPage(ctx, ProductFormData.of(PutProduct.empty()), PutProductType.Add))

    @GetMapping(Paths.Products.EDIT)
    fun getEditProductPage(
        @Context ctx: PageContext,
        @PathVariable("id") rawProductId: String): ResponseEntity<String> {
        val product = productsFacade.byId(ProductId.of(rawProductId))
                ?: return redirect(Paths.Products.INDEX)

        return html(putProductPage(ctx, ProductFormData.of(product), PutProductType.Edit))
    }

    @GetMapping(Paths.Products.SEARCH)
    fun getSearchProductFragment(
        @RequestParam q: String,
        @Context ctx: PageContext): ResponseEntity<String> {
        val products = productsFacade.byQuery(q)

        return fragment(buildString {
            appendHTML().filter { if(it.tagName == "table" || it.tagName == "tbody") PredicateResult.SKIP else PredicateResult.PASS }.table {
                productsTableBody(products)
            }
        })
    }

    @PostMapping(Paths.Products.INDEX)
    fun postProduct(
        @Context ctx: PageContext,
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