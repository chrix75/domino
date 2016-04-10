package domain.global.services

import domain.global.entities.Entity
import neo4j.utils.Neo4jSessionFactory
import org.neo4j.ogm.session.Session

/**
 * Created by batman on 09/04/2016.
 *
 */
abstract class GenericCRUD<T: Entity>(protected val session: Session) {
    enum class FETCH_TYPE(val type: Int) { EAGER(-1), LAZY(0) }

    abstract fun getEntityClass(): Class<T>

    fun find(id: Long, fetchType: FETCH_TYPE = FETCH_TYPE.EAGER): T {
        return session.load(getEntityClass(), id, fetchType.type)
    }

    fun findAll(cls: Class<T>, fetchType: FETCH_TYPE = FETCH_TYPE.EAGER): Collection<T> {
        return session.loadAll(cls, fetchType.type)
    }

    fun save(o: T, fetchType: FETCH_TYPE = FETCH_TYPE.EAGER) {
        session.save(o, fetchType.type)
    }

    open fun delete(o: T) {
        session.delete(o)
    }
}