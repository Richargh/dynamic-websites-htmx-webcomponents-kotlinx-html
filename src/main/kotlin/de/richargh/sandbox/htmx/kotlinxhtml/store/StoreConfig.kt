package de.richargh.sandbox.htmx.kotlinxhtml.store

import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.StoreFacade
import de.richargh.sandbox.htmx.kotlinxhtml.store.infra.InMemoryBasket
import de.richargh.sandbox.htmx.kotlinxhtml.store.infra.InMemoryStock
import de.richargh.sandbox.htmx.kotlinxhtml.store.web.BasketController
import de.richargh.sandbox.htmx.kotlinxhtml.store.web.StoreController
import org.springframework.context.support.BeanDefinitionDsl

fun BeanDefinitionDsl.storeConfig() {
    bean<StoreController>()
    bean<BasketController>()
    bean<StoreFacade>()
    bean<InMemoryStock>()
    bean<InMemoryBasket>()
}