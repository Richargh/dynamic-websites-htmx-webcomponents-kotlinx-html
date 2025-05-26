package de.richargh.sandbox.htmx.kotlinxhtml.greeting.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.AnonContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.AnonPageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.fragmentOfMain
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.html
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class GreetingController {

    @GetMapping(Paths.Greeting.INDEX)
    fun getGreetingPage(
        @AnonContext ctx: AnonPageContext
    ) = html(greetingPage(ctx))

    @GetMapping(Paths.Greeting.OPEN_MODAL)
    fun getModal(
        @AnonContext ctx: AnonPageContext
    ) = fragmentOfMain {
        greetingModalFragment(ctx)
    }

    @PostMapping(Paths.Greeting.SUBMIT_MODAL)
    fun post(
        @AnonContext ctx: AnonPageContext
    ): ResponseEntity<String> {
        println("I was submitted")
        return ResponseEntity.status(200).body("")
    }

}
