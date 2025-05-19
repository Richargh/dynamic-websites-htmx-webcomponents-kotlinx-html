package de.richargh.sandbox.htmx.kotlinxhtml.store.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.repo.domain.Repository
import de.richargh.sandbox.htmx.kotlinxhtml.product.domain.ProductId

interface Stock: Repository<ProductId, Item>