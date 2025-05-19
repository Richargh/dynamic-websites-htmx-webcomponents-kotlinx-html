package de.richargh.sandbox.htmx.kotlinxhtml

import de.richargh.sandbox.htmx.kotlinxhtml.greeting.greetingConfig
import de.richargh.sandbox.htmx.kotlinxhtml.login.web.loginConfig
import de.richargh.sandbox.htmx.kotlinxhtml.product.productConfig
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.context.support.beans

@Import(WebConfig::class, WebSecurityConfig::class)
@EnableAutoConfiguration
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args) {
		addInitializers(beans {
			bean<IndexController>()
			loginConfig()
			greetingConfig()
			productConfig()
		})
	}
}
