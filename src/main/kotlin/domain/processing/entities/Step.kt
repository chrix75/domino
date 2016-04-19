package domain.processing.entities

import domain.global.entities.NamedEntity
import domain.global.validators.EntityValidator
import domain.global.validators.StepValidator
import domain.global.validators.Validable
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
@NodeEntity
class Step(_name: String = "") : NamedEntity(_name), Validable<Step> {


    @Property
    var state = RunningState.TO_CONFIGURE

    @Relationship(type = "HAS_STEP")
    var steps = setOf<Step>()

    @Relationship(type = "HAS_PARAMETER")
    var parameters = setOf<Parameter>()

    /**
     * Returns an instance of the validator checks the validity of the step before saving into the DB.
     */
    override fun buildValidator(): EntityValidator<Step> {
        return StepValidator()
    }

    /**
     * Resets the id of the current steps and the id for all its parameters and substeps.
     */
    override fun resetId() {
        id = null

        parameters.forEach { it.resetId() }

        steps.forEach { it.resetId() }
    }
}
