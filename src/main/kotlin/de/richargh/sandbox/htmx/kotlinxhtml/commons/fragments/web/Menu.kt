package de.richargh.sandbox.htmx.kotlinxhtml.commons.fragments.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.commons.widgets.web.relativeTime
import de.richargh.sandbox.htmx.kotlinxhtml.store.web.basketCountFragment
import kotlinx.html.*
import java.time.ZonedDateTime

@HtmlTagMarker
fun FlowContent.menu(ctx: PageContext) = nav {
    attributes["hx-boost"] = "true"
    ul {
        li {
            strong {
                +"Dynamic Web with Htmx"
            }
        }
        li {
            +"Last full replace: "
            relativeTime(ZonedDateTime.now())
        }
    }
    ul {
        li {
            a(href = Paths.Greeting.INDEX) { +"Greeting" }
        }
        li {
            a(href = Paths.Products.INDEX) { +"Products" }
        }
        li {
            a(href = Paths.Store.INDEX) { +"Store" }
        }
        li {
            basketCountFragment(ctx.userData.basketCount)
        }
        if (ctx.user == null) {
            li {
                a(href = Paths.Login.INDEX) { +"Login" }
            }
        } else {
            li {
                details(classes = "dropdown"){
                    summary {
                        +"Account"
                    }
                    ul {
                        attributes["dir"] = "rtl"
                        li {
                            a {
                                +"Profile"
                            }
                        }
                        li {
                            form {
                                action = Paths.Logout.INDEX
                                method = FormMethod.post
                                button(type = ButtonType.submit, classes = "secondary") {
                                    value = "Logout"
                                    +"Logout ${ctx.user.userName.rawValue}"
                                }
                                input(type = InputType.hidden, name = "_csrf") { value = ctx.csrfToken!!.rawValue }
                            }
                        }
                    }
                }

            }
        }
    }
}
