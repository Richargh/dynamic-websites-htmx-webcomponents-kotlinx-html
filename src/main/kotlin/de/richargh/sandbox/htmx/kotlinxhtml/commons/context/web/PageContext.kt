package de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web

import com.fasterxml.jackson.databind.ObjectMapper

sealed interface PageContext {
        val user: PageUser?
        val userData: PageUserData?
        val csrfToken: CsrfFormToken?
        val services: PageContextServices
}

data class AuthPageContext(
        override val user: PageUser,
        override val userData: PageUserData,
        override val csrfToken: CsrfFormToken?,
        override val services: PageContextServices
) : PageContext

data class AnonPageContext(
        override val user: PageUser?,
        override val userData: PageUserData?,
        override val csrfToken: CsrfFormToken?,
        override val services: PageContextServices
) : PageContext

class PageContextServices(
        val mapper: ObjectMapper
)

data class PageUser(
        val userName: UserName
)

data class PageUserData(
        val basketCount: Int
)

data class UserName(val rawValue: String)