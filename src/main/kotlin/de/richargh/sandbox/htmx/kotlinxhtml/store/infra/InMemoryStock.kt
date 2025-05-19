package de.richargh.sandbox.htmx.kotlinxhtml.store.infra

import de.richargh.sandbox.htmx.kotlinxhtml.commons.repo.domain.BaseInMemoryRepository
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.Item
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.Stock

class InMemoryStock: Stock, BaseInMemoryRepository<ProductId, Item>()