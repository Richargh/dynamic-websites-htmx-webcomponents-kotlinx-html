package de.richargh.sandbox.htmx.kotlinxhtml.product.infra

import de.richargh.sandbox.htmx.kotlinxhtml.commons.repo.domain.BaseInMemoryRepository
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Product
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.Products

class InMemoryProducts: Products, BaseInMemoryRepository<ProductId, Product>()