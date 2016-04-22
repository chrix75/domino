package domain.processing.entities.objects

import domain.global.entities.NamedEntity
import domain.global.validators.EntityValidator
import domain.global.validators.ParameterValidator
import domain.global.validators.Validable
import domain.processing.entities.Parameter
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

/**
 * The parameter entity.
 *
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
@NodeEntity(label = "Parameter")
class ParameterEntity(_name: String = "") : NamedEntity(_name), Validable<ParameterEntity>, Parameter {
    override var value: String? = null
        get() = field
        set(value) {
            field = value
            parameters.forEach { it.value = value }
        }


    @Relationship(type = "INITIALIZES", direction = Relationship.OUTGOING)
    override var parameters: Set<ParameterEntity> = setOf()

    /**
     * Returns an instance of the validator checks if a parameter is valid before saving into the DB.
     */
    override fun buildValidator(): EntityValidator<ParameterEntity> {
        return ParameterValidator()
    }

    /**
     * Resets the id to null of the current parameter.
     */
    override fun resetId() {
        id = null
    }
}
