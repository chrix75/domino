package domain.processing.entities.objects

import domain.global.entities.NamedEntity
import domain.global.validators.EntityValidator
import domain.global.validators.OutputValidator
import domain.global.validators.Validable
import org.neo4j.ogm.annotation.NodeEntity

/**
 * Created by Christian Sperandio on 03/05/2016.
 *
 */
@NodeEntity(label = "Output")
class OutputEntity(_name: String = "") : NamedEntity(_name), Validable<OutputEntity> {
    var value: String? = null

    override fun resetId() {
        id = null
    }

    override fun buildValidator(): EntityValidator<OutputEntity> {
        return OutputValidator()
    }
}
