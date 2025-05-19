package de.richargh.sandbox.htmx.kotlinxhtml

import de.richargh.sandbox.htmx.kotlinxhtml.commons.money.domain.Euro
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Product
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Products
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.Item
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.Stock
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner

class ApplicationDemoDataSupplier(
    private val products: Products,
    private val stock: Stock,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        createInitialProducts()
        createInitialStock()
    }

    private fun createInitialProducts(){
        products.putAll(productInStockDemoData.map { it.first })
    }

    private fun createInitialStock(){
        stock.putAll(productInStockDemoData.map { Item(it.first.id, it.first, it.second) })
    }
}

private val productInStockDemoData = sequenceOf(
    Product(ProductId.unique(), "Cup", Euro.ofEuros(1)) to 1,
    Product(ProductId.unique(), "Car", Euro.ofEuros(20_000)) to 2,
    Product(ProductId.unique(), "Mac", Euro.ofEuros(2_000)) to 3,
    Product(ProductId.unique(), "Tractor", Euro.ofEuros(100_000)) to 4,
    Product(ProductId.unique(), "Desk", Euro.ofEuros(500)) to 5,
    Product(ProductId.unique(), "Flask", Euro.ofEuros(2)) to 6,
    Product(ProductId.unique(), "Screen", Euro.ofEuros(600)) to 7,
    Product(ProductId.unique(), "USB-C Cable", Euro.ofEuros(50)) to 8,
    Product(ProductId.unique(), "USB-A Cable", Euro.ofEuros(5)) to 9,
    Product(ProductId.unique(), "Phone", Euro.ofEuros(1_200)) to 10,
) + generateSequence(1) { it + 1 }.take(1000).map {
    Product(ProductId.unique(), "Bottle worth $it EUR", Euro.ofEuros(it)) to 1000/it
}