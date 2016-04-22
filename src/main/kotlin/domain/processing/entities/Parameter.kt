package domain.processing.entities

import domain.processing.entities.objects.ParameterEntity

/**
 * Created by batman on 20/04/2016.
 *
 */
interface Parameter {
    var value: String?
    val parameters: Set<Parameter>
    val name: String?

    fun resetId()
}
