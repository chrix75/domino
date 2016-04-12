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

    @Relationship(type = "HAS_STEP")
    private var _steps = mutableSetOf<Step>()

    @Relationship(type = "HAS_PARAMETER")
    private var _parameters = mutableSetOf<Parameter>()

    @Property
    var state = RunningState.TO_CONFIGURE

    @Transient
    var steps = setOf<Step>()
        get() = _steps
        set(value) {
            _steps = value.toMutableSet()
            field = _steps
        }


    @Transient
    var parameters = setOf<Parameter>()
        get() = _parameters
        set(value) {
            _parameters = value.toMutableSet()
            field = _parameters
        }

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
