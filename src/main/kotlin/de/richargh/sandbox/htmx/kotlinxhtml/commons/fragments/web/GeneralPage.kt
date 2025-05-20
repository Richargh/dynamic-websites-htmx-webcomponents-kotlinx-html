package de.richargh.sandbox.htmx.kotlinxhtml.commons.fragments.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import kotlinx.html.*
import kotlinx.html.stream.appendHTML

fun generalPage(ctx: PageContext, content: MAIN.() -> Unit): String {
    return buildString {
        appendHTML(xhtmlCompatible = true).html {
            head {
                meta { charset = "utf-8" }
                meta { name = "viewport"; this.content = "width=device-width, initial-scale=1" }
                title { +"Htmx Demo" }
                title { +"Htmx Demo" }
                script(type = "importmap") {
                    unsafe {
                        raw(ctx.services.mapper.writeValueAsString(importMap))
                    }
                }
                link(href = "/public/pico.min.css", "stylesheet")
                link(href = "/public/pagination.css", "stylesheet")
                script(type = "text/javascript", src = "/public/htmx.min.js") {
                    defer = true
                }
                script(type = "text/javascript", src = "/public/github-elements/relative-time.js") {
                    defer = true
                }
            }
            body {
                header(classes = "is-fixed-above-lg is-fixed") {
                    div(classes = "container"){
                        menu(ctx)
                    }
                }
                main(classes = "container") {
                    content()
                }
                footer {
                    div(classes = "container"){
                        p {
                            +"Sandbox Website"
                        }
                    }
                }
            }
        }
    }
}

private val importMap = mapOf(
    "htmx" to "/public/htmx.min.js",
)