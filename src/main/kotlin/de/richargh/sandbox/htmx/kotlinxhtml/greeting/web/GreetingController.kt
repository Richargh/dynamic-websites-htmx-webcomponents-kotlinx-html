package de.richargh.sandbox.htmx.kotlinxhtml.greeting.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.AnonContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.AnonPageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.html
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class GreetingController {

    @GetMapping(Paths.Greeting.INDEX)
    fun greeting(
        @AnonContext ctx: AnonPageContext
    ) =
        html(makeGreetingPage(ctx))

}
