package domain.processing.entities.objects

import domain.global.entities.NamedEntity
import domain.global.validators.EntityValidator
import domain.global.validators.StepValidator
import domain.global.validators.Validable
import domain.processing.entities.Parameter
import domain.processing.entities.Step
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship
import org.neo4j.ogm.annotation.Transient

/**
 * The Step class is the Step entity saved into the DB.
 *
 * A step has one parameter at least and may have substeps.
 *
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
@NodeEntity(label = "Step")
class StepEntity(_name: String = "") : NamedEntity(_name), Validable<StepEntity>, Step {


    @Property
    override var state: RunningState? = null

    @Relationship(type = "HAS_STEP", direction = Relationship.OUTGOING)
    override var steps: Set<StepEntity>? = null

    @Relationship(type = "HAS_PARAMETER", direction = Relationship.OUTGOING)
    override var parameters: Set<ParameterEntity>? = null

    override fun toString(): String {
        return "[Step ID=$id Name=$name}"
    }

    /**
     * Returns an instance of the validator checks the validity of the step before saving into the DB.
     */
    override fun buildValidator(): EntityValidator<StepEntity> {
        return StepValidator()
    }

    /**
     * Resets the id of the current steps and the id for all its parameters and substeps.
     */
    override fun resetId() {
        id = null


        parameters?.forEach { it.resetId() }

        steps?.forEach {
            it.resetId()
        }
    }
}
