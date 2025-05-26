package de.richargh.sandbox.htmx.kotlinxhtml

import de.richargh.sandbox.htmx.kotlinxhtml.commons.widgets.widgetConfig
import de.richargh.sandbox.htmx.kotlinxhtml.greeting.greetingConfig
import de.richargh.sandbox.htmx.kotlinxhtml.login.web.loginConfig
import de.richargh.sandbox.htmx.kotlinxhtml.product.productConfig
import de.richargh.sandbox.htmx.kotlinxhtml.store.storeConfig
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

class ApplicationTestContext : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(applicationContext: GenericApplicationContext) {
        beans {
            bean<IndexController>()
            loginConfig()
            greetingConfig()
            productConfig()
            storeConfig()
            widgetConfig()
        }.initialize(applicationContext)
    }

}