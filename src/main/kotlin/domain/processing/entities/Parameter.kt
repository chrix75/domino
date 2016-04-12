package domain.processing.entities

import domain.global.entities.NamedEntity
import domain.global.validators.EntityValidator
import domain.global.validators.ParameterValidator
import domain.global.validators.Validable
import org.neo4j.ogm.annotation.NodeEntity

/**
 * The parameter entity.
 *
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
@NodeEntity
class Parameter(_name: String = "") : NamedEntity(_name), Validable<Parameter> {
    var value: String = ""

    /**
     * Returns an instance of the validator checks if a parameter is valid before saving into the DB.
     */
    override fun buildValidator(): EntityValidator<Parameter> {
        return ParameterValidator()
    }

    /**
     * Resets the id to null of the current parameter.
     */
    override fun resetId() {
        id = null
    }
}
