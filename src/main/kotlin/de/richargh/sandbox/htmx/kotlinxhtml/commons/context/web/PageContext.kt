package de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web

data class PageContext(
        val user: PageUser?,
        val csrfToken: CsrfFormToken?
)

data class PageUser(
        val userName: String
)