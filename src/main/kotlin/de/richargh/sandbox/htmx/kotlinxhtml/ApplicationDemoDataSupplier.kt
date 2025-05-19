package de.richargh.sandbox.htmx.kotlinxhtml

import de.richargh.sandbox.htmx.kotlinxhtml.commons.money.domain.Euro
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Product
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Products
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner

class ApplicationDemoDataSupplier(
    private val products: Products
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        createInitialProducts()
    }

    private fun createInitialProducts(){
        products.putAll(productDemoData)
    }
}

private val productDemoData = sequenceOf(
    Product(ProductId.unique(), "Cup", Euro.ofEuros(1), 1),
    Product(ProductId.unique(), "Car", Euro.ofEuros(20_000), 2),
    Product(ProductId.unique(), "Mac", Euro.ofEuros(2_000), 3),
    Product(ProductId.unique(), "Tractor", Euro.ofEuros(100_000), 4),
    Product(ProductId.unique(), "Desk", Euro.ofEuros(500), 5),
    Product(ProductId.unique(), "Flask", Euro.ofEuros(2), 6),
    Product(ProductId.unique(), "Screen", Euro.ofEuros(600), 7),
    Product(ProductId.unique(), "USB-C Cable", Euro.ofEuros(50), 8),
    Product(ProductId.unique(), "USB-A Cable", Euro.ofEuros(5), 9),
    Product(ProductId.unique(), "Phone", Euro.ofEuros(1_200), 10),
) + generateSequence(1) { it + 1 }.take(1000).map {
    Product(ProductId.unique(), "Bottle worth $it EUR", Euro.ofEuros(it), 1000/it)
}