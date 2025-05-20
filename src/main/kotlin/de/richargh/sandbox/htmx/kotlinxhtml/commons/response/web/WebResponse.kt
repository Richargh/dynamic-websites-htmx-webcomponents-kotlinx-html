package de.richargh.sandbox.htmx.kotlinxhtml.commons.response.web

import kotlinx.html.*
import kotlinx.html.consumers.PredicateResult
import kotlinx.html.consumers.filter
import kotlinx.html.stream.appendHTML
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.net.URI

fun html(body: String, status: HttpStatus = HttpStatus.OK): ResponseEntity<String> =
    ResponseEntity.status(status).contentType(MediaType.TEXT_HTML).body(body)

fun fragment(body: String, status: HttpStatus = HttpStatus.OK): ResponseEntity<String> =
    ResponseEntity.status(status).contentType(MediaType.TEXT_HTML).body(body)

// TODO there must be some way to be type-safe but write the fragments much much smarter
fun fragmentOfMain(block: FlowContent.() -> Unit): ResponseEntity<String> {
    val responseBuilder = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_HTML)

    return responseBuilder.body(buildString {
        appendHTML().filter { if (it.tagName == "main") PredicateResult.SKIP else PredicateResult.PASS }.main {
            block()
        }
    })
}

fun fragmentOfTable(block: TABLE.() -> Unit): ResponseEntity<String> {
    val responseBuilder = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_HTML)

    return responseBuilder.body(buildString {
        appendHTML().filter {
            if (it.tagName == "table") PredicateResult.SKIP
            else PredicateResult.PASS
        }.table {
            block()
        }
    })
}

fun fragmentOfTbody(header: Pair<String, String>? = null, block: TBODY.() -> Unit): ResponseEntity<String> {
    val responseBuilder = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_HTML)
    if(header != null) responseBuilder.header(header.first, header.second)

    return responseBuilder.body(buildString {
        appendHTML().filter {
            if (it.tagName == "tbody") PredicateResult.SKIP
            else PredicateResult.PASS
        }.tbody {
            block()
        }
    })
}

fun redirect(location: String): ResponseEntity<String> =
    ResponseEntity.status(HttpStatus.SEE_OTHER).location(URI.create(location)).build()