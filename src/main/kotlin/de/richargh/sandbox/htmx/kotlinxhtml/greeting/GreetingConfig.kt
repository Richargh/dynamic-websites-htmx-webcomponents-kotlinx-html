package de.richargh.sandbox.htmx.kotlinxhtml.greeting

import de.richargh.sandbox.htmx.kotlinxhtml.greeting.domain.GreetingFacade
import de.richargh.sandbox.htmx.kotlinxhtml.greeting.web.GreetingController
import org.springframework.context.support.BeanDefinitionDsl

fun BeanDefinitionDsl.greetingConfig() {
    bean<GreetingController>()
    bean<GreetingFacade>()
}