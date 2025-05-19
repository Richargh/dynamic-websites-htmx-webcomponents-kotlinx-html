package de.richargh.sandbox.htmx.kotlinxhtml.greeting.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.fragments.web.generalPage
import kotlinx.html.h1
import kotlinx.html.p

fun makeGreetingPage(ctx: PageContext) = generalPage(ctx) {
    h1 { +"Greeting" }
    p {
        attributes["data-testid"] = "message"
        +"Hello ${ctx.user?.userName ?: "Anonymous"}"
    }
}