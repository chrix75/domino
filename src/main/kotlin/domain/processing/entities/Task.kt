package domain.processing.entities

import domain.processing.entities.objects.RunningState

/**
 * Created by batman on 20/04/2016.
 *
 */
interface Task {
    fun resetId()

    var state: RunningState?
    val steps: Set<Step>?
    val parameters: Set<Parameter>?
    var name: String?

    fun updateParameter(parameterName: String, value: String) {
        parameters?.filter { it.name == parameterName }?.forEach { it.value = value }
    }

    fun stepByName(name: String): Step? = steps?.first { it.name == name }

    fun parameterByName(name: String): Parameter? = parameters?.first { it.name == name }

}