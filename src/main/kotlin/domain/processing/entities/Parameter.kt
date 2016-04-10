package domain.processing.entities

import domain.global.entities.NamedEntity
import domain.global.validators.EntityValidator
import domain.global.validators.ParameterValidator
import domain.global.validators.Validable
import org.neo4j.ogm.annotation.NodeEntity

/**
 * Created by batman on 09/04/2016.
 *
 */
@NodeEntity
class Parameter(_name: String = "") : NamedEntity(_name), Validable<Parameter> {
    var value: String = ""

    override fun buildValidator(): EntityValidator<Parameter> {
        return ParameterValidator()
    }

    override fun resetId() {
        id = null
    }
}