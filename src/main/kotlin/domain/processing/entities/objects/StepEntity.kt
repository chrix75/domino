package domain.processing.entities.objects

import domain.global.entities.NamedEntity
import domain.global.validators.EntityValidator
import domain.global.validators.StepValidator
import domain.global.validators.Validable
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship

/**
 * The Step class is the Step entity saved into the DB.
 *
 * A step has one parameter at least and may have substeps.
 *
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
@NodeEntity(label = "Step")
class StepEntity(_name: String = "") : NamedEntity(_name), Validable<StepEntity> {


    @Property
    var state: RunningState? = null

    @Relationship(type = "HAS_STEP", direction = Relationship.OUTGOING)
    var steps: Set<StepEntity>? = null

    @Relationship(type = "HAS_PARAMETER", direction = Relationship.OUTGOING)
    var parameters: Set<ParameterEntity>? = null

    @Relationship(type = "PRODUCES", direction = Relationship.OUTGOING)
    var outputs: Set<OutputEntity>? = null


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

        outputs?.forEach { it.resetId() }
    }
}
