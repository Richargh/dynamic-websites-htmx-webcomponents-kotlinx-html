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
                link(href = "/public/simple.min.css", "stylesheet")
                script(type = "text/javascript", src = "/public/htmx.min.js") {
                    defer = true
                }
            }
            body {
                header {
                    menu(ctx)
                }
                main {
                    content()
                }
                footer {
                    p {
                        +"Sandbox Website"
                    }
                }
            }
        }
    }
}