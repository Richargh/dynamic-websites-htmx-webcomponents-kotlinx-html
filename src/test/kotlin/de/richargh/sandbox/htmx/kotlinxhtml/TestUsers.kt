package de.richargh.sandbox.htmx.kotlinxhtml

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.request.RequestPostProcessor

fun vin(): RequestPostProcessor {
    return SecurityMockMvcRequestPostProcessors.user("vin").roles("ADMIN")
}