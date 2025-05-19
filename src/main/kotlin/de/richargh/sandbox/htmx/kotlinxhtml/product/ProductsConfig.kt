package de.richargh.sandbox.htmx.kotlinxhtml.product

import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductsFacade
import de.richargh.sandbox.htmx.kotlinxhtml.product.web.ProductsController
import org.springframework.context.support.BeanDefinitionDsl

fun BeanDefinitionDsl.productConfig() {
    bean<ProductsController>()
    bean<ProductsFacade>()
}