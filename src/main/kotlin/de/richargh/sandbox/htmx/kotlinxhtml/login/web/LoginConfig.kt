package de.richargh.sandbox.htmx.kotlinxhtml.login.web

import org.springframework.context.support.BeanDefinitionDsl

fun BeanDefinitionDsl.loginConfig() {
    bean<LoginController>()
}