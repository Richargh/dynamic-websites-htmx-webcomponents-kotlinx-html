package de.richargh.sandbox.htmx.kotlinxhtml.commons.widgets.web

import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class WidgetController {

    @RequestMapping(Paths.Widgets.GET_ONE)
    fun getGreetingPage(@PathVariable("jsFileName") jsFileName: String): ResponseEntity<String> {
        println("Getting $jsFileName")

        val result = when (jsFileName) {
            nativeModalFileName -> nativeModalCode
            else -> "Unknown"
        }

        return ResponseEntity.status(200)
            .contentType(MediaType.valueOf("text/javascript"))
            .body(result)
    }
}