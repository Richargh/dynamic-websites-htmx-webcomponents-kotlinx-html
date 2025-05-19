package de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain

import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

data class PagedCollection<out E>(
    val limit: Int,
    val offset: Int,
    val total: Int,
    val collection: Collection<E>): Collection<E> by collection {

    val pageCount = max(ceil(total.toDouble() / limit.toDouble()).toInt(), 1)
    val currentPage = (offset / limit) + 1

}
