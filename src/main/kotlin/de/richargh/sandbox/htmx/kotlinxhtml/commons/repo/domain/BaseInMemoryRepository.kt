package de.richargh.sandbox.htmx.kotlinxhtml.commons.repo.domain

import de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain.PagedCollection
import java.util.concurrent.ConcurrentHashMap

abstract class BaseInMemoryRepository<TId, TEntity: Entity<TId>>: Repository<TId, TEntity> {

    private val allEntities = ConcurrentHashMap<TId, TEntity>()

    override fun all(limit: Int, offset: Int): PagedCollection<TEntity> {
        return byQuery("", limit, offset)
    }

    override operator fun get(entityId: TId): TEntity? {
        return allEntities[entityId]
    }

    override fun byQuery(q: String, limit: Int, offset: Int): PagedCollection<TEntity> {
        var total = 0
        val collection = allEntities.asSequence()
            .filter { it.value.matchesQuery(q) }
            .filterIndexed { index, _ ->
                total++

                index in offset until limit + offset
            }
            .map { it.value }
            .toList()

        return PagedCollection(limit, offset, total, collection)
    }

    override operator fun set(id: TId, entity: TEntity) {
        allEntities[entity.id] = entity
    }

    override fun putAll(entities: Sequence<TEntity>) {
        entities.forEach {
            allEntities[it.id] = it
        }
    }
}