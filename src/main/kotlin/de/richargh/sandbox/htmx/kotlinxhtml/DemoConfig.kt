package de.richargh.sandbox.htmx.kotlinxhtml

import org.springframework.context.support.BeanDefinitionDsl

fun BeanDefinitionDsl.demoConfig() {
    bean<ApplicationDemoDataSupplier>()
}