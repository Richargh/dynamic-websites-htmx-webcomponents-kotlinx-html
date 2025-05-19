package de.richargh.sandbox.htmx.kotlinxhtml.commons.repo.domain

interface Entity<TId> {
    val id: TId
    fun matchesQuery(query: String): Boolean
}