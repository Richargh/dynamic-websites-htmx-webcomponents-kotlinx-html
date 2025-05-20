package de.richargh.sandbox.htmx.kotlinxhtml.commons.widgets.web

import kotlinx.html.FlowContent
import kotlinx.html.HTMLTag
import kotlinx.html.HtmlInlineTag
import kotlinx.html.HtmlTagMarker
import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class RELATIVE_TIME(initialAttributes: Map<String, String>, consumer: TagConsumer<*>) :
    HTMLTag(
        "relative-time", consumer, initialAttributes,
        inlineTag = true,
        emptyTag = false
    ), HtmlInlineTag

@HtmlTagMarker
inline fun FlowContent.relativeTime(datetime: ZonedDateTime, classes: String? = null, crossinline block: RELATIVE_TIME.() -> Unit = {}): Unit =
    RELATIVE_TIME(
        attributesMapOf("class", classes) + mapOf("datetime" to datetime.format(formatter)),
        consumer
    ).visit(block)


val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz").withZone(ZoneOffset.UTC)