package de.richargh.sandbox.htmx.kotlinxhtml.login.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.Context
import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web.html
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@Controller
class LoginController {
    @GetMapping(Paths.Login.INDEX)
    fun getLoginPage(
        @Context ctx: PageContext,
        @RequestParam error: String?,
        @RequestParam logout: String?,
        @RequestHeader("HX-Request") isHxRequest: Boolean = false,
        @RequestHeader("HX-Target") hxTarget: String?): ResponseEntity<String> {
        return if(isHxRequest && hxTarget != null && !hxTarget.contains(Paths.Login.INDEX)){
            html("Please refresh this page and login")
        } else {
            html(loginPage(ctx, loginError = error != null, wasLoggedOut = logout != null))
        }
    }
}