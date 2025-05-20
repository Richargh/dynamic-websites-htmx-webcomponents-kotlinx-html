package de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web

import com.fasterxml.jackson.databind.ObjectMapper

data class PageContext(
        val user: PageUser?,
        val userData: PageUserData,
        val csrfToken: CsrfFormToken?,
        val services: PageContextServices
)

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