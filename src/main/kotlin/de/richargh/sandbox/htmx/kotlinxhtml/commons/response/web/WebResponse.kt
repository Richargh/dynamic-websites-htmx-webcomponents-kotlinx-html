package de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.net.URI

fun html(body: String, status: HttpStatus = HttpStatus.OK): ResponseEntity<String> =
        ResponseEntity.status(status).contentType(MediaType.TEXT_HTML).body(body)

fun fragment(body: String, status: HttpStatus = HttpStatus.OK): ResponseEntity<String> =
        ResponseEntity.status(status).contentType(MediaType.TEXT_HTML).body(body)

fun redirect(location: String): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.SEE_OTHER).location(URI.create(location)).build()