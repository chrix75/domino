package domain.processing.entities

import domain.processing.entities.objects.RunningState

/**
 * Created by Christian Sperandio on 20/04/2016.
 *
 */
interface Step {
    var state: RunningState?
    val steps: Set<Step>?
    val parameters: Set<Parameter>?
    val name: String?

    fun resetId()

    fun stepByName(name: String): Step? = steps?.first { it.name == name }

    fun parameterByName(name: String): Parameter? = parameters?.first { it.name == name }

}
