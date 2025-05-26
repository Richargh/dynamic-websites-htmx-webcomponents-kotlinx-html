package de.richargh.sandbox.htmx.kotlinxhtml.commons.widgets

import de.richargh.sandbox.htmx.kotlinxhtml.commons.widgets.web.WidgetController
import org.springframework.context.support.BeanDefinitionDsl

fun BeanDefinitionDsl.widgetConfig() {
    bean<WidgetController>()
}