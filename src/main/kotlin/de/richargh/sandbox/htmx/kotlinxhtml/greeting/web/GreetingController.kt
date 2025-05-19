package de.richargh.sandbox.htmx.kotlinxhtml.greeting.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.Context
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.html
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class GreetingController {

    @GetMapping(Paths.Greeting.INDEX)
    fun greeting(
            @Context ctx: PageContext
    ) =
            html(makeGreetingPage(ctx))

}
