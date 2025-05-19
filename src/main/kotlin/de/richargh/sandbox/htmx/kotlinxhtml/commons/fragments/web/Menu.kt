package de.richargh.sandbox.htmx.kotlinxhtml.commons.fragments.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import kotlinx.html.*

@HtmlTagMarker
fun HEADER.menu(ctx: PageContext) = nav {
    attributes["hx-boost"] = "true"
    ul {
        li {
            a(href = Paths.Greeting.INDEX) { +"Greeting" }
        }
        li {
            a(href = Paths.Products.INDEX) { +"Products" }
        }
        if(ctx.user == null) {
            li {
                a(href = Paths.Login.INDEX) { +"Login" }
            }
        } else {
            form {
                action = Paths.Logout.INDEX
                method = FormMethod.post
                button(type = ButtonType.submit) {
                    value = "Logout"
                    +"Logout ${ctx.user.userName}"
                }
                input(type = InputType.hidden, name = "_csrf") { value = ctx.csrfToken!!.rawValue }
            }
        }
    }
}
