package de.richargh.sandbox.htmx.kotlinxhtml.greeting.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web.PageContext
import de.richargh.sandbox.htmx.kotlinxhtml.commons.fragments.web.WebIds
import de.richargh.sandbox.htmx.kotlinxhtml.commons.fragments.web.generalPage
import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.commons.widgets.web.nativeModal
import kotlinx.html.*

fun greetingPage(ctx: PageContext) = generalPage(ctx) {
    h1 { +"Greeting" }
    p {
        attributes["data-testid"] = "message"
        +"Hello ${ctx.user?.userName?.rawValue ?: "Anonymous"}"
    }

    form {
        input(type = InputType.hidden, name = "_csrf") { value = ctx.csrfToken!!.rawValue }

        input(type = InputType.submit) {
            value = "Show Modal"
            attributes["hx-get"] = Paths.Greeting.OPEN_MODAL
            attributes["hx-target"] = "#${WebIds.CurrentModal.rawValue}"
        }
    }
}

fun FlowContent.greetingModalFragment(ctx: PageContext) = nativeModal {
    dialog {
        form {
            input(type = InputType.hidden, name = "_csrf") { value = ctx.csrfToken!!.rawValue }

            article {
                h2 {
                    +"Test Modal"
                }
                p {
                    +"Thank you for opening this modal. Press the buttons below"
                }

                footer {
                    div(classes = "grid") {
                        input(type = InputType.reset) {
                            value = "Cancel"
                        }
                        input(type = InputType.submit) {
                            value = "Confirm"
                            attributes["hx-post"] = Paths.Greeting.SUBMIT_MODAL
                            attributes["hx-target"] = "#${WebIds.CurrentModal.rawValue}"
                        }
                    }
                }
            }
        }
    }
}