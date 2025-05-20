package de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web

data class PageContext(
        val user: PageUser?,
        val userData: PageUserData,
        val csrfToken: CsrfFormToken?
)

data class PageUser(
        val userName: UserName
)

data class PageUserData(
        val basketCount: Int
)

data class UserName(val rawValue: String)