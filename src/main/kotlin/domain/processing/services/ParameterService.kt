package domain.processing.services

import domain.global.services.GenericCRUD
import domain.processing.entities.objects.ParameterEntity
import org.neo4j.ogm.session.Session

/**
 * The class ParameterService manages the parameters persistence.
 *
 * @constructor session The Neo4J session used by this service.
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class ParameterService(session: Session) : GenericCRUD<ParameterEntity>(session) {

    override fun getEntityClass(): Class<ParameterEntity> {
        return ParameterEntity::class.java
    }
}
