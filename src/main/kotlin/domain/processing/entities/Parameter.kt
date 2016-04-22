package domain.processing.entities

import domain.processing.entities.objects.ParameterEntity

/**
 * Created by Christian Sperandio on 20/04/2016.
 *
 * Interface for task or step parameters.
 *
 * @property value The value of the parameter.
 * @property parameters The parameters are initialized by the current parameter.
 * @property name The name of the parameter.
 *
 */
interface Parameter {
    var value: String?
    val parameters: Set<Parameter>
    val name: String?

    /**
     * Set the parameter ID to null.
     */
    fun resetId()
}
