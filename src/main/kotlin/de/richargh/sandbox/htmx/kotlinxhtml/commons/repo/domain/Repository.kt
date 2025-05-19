package de.richargh.sandbox.htmx.kotlinxhtml.commons.repo.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection

interface Repository<TId, TEntity: Entity<TId>> {
    operator fun get(id: TId): TEntity?
    fun all(limit: Int = 10, offset: Int = 0): PagedCollection<TEntity>
    fun byQuery(q: String, limit: Int = 10, offset: Int = 0): PagedCollection<TEntity>

    operator fun set(id: TId, entity: TEntity)
    fun putAll(entities: Sequence<TEntity>)
}