package domain.processing.services

import domain.global.services.GenericCRUD
import domain.processing.entities.objects.StepEntity
import neo4j.utils.TransactionManager
import org.neo4j.ogm.session.Session

/**
 * The class StepService manages the step persistence.
 *
 * @constructor session The Neo4J session used by this service.
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class StepService(session: Session) : GenericCRUD<StepEntity>(session) {

    private val parameterService = ParameterService(session)

    override fun getEntityClass(): Class<StepEntity> {
        return StepEntity::class.java
    }

    override fun delete(o: StepEntity) {
        var tx = TransactionManager(session)
        tx.beginTransaction()

        try {

            o.steps?.forEach { substep ->
                delete(substep)
            }

            o.parameters?.forEach { param ->
                parameterService.delete(param)
            }

            super.delete(o)

            tx.commit()

        } catch (e: Exception) {
            tx.rollback()
            throw e
        } finally {
            tx.close()
        }
    }
}
