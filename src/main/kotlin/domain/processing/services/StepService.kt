package domain.processing.services

import domain.global.services.GenericCRUD
import domain.processing.entities.Step
import neo4j.utils.TransactionManager
import org.neo4j.ogm.session.Session

/**
 * Created by batman on 10/04/2016.
 *
 */
class StepService(session: Session) : GenericCRUD<Step>(session) {

    private val parameterService = ParameterService(session)

    override fun getEntityClass(): Class<Step> {
        return Step::class.java
    }

    override fun delete(o: Step) {
        var tx = TransactionManager(session)
        tx.beginTransaction()

        try {

            o.steps.forEach { substep ->
                delete(substep)
            }

            o.parameters.forEach { param ->
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