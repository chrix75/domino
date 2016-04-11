package domain.processing.entities

import domain.global.entities.NamedEntity
import domain.global.validators.EntityValidator
import domain.global.validators.StepValidator
import domain.global.validators.Validable
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.neo4j.ogm.annotation.Transient

/**
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
@NodeEntity
class Step(_name: String = "") : NamedEntity(_name), Validable<Step> {

    @Relationship(type = "HAS_STEP")
    private var _steps = mutableSetOf<Step>()

    @Relationship(type = "HAS_PARAMETER")
    private var _parameters = mutableSetOf<Parameter>()

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

    override fun buildValidator(): EntityValidator<Step> {
        return StepValidator()
    }

    override fun resetId() {
        id = null

        parameters.forEach { it.resetId() }

        steps.forEach { it.resetId() }
    }
}
