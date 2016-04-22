package domain.processing.entities

import domain.processing.entities.objects.RunningState

/**
 * Created by Christian Sperandio on 20/04/2016.
 *
 * Interface for the tasks.
 *
 * @property state The current task state.
 * @property steps The steps of the task.
 * @property parameters The root task parameters used for step parameters initalization.
 * @property name Task name
 *
 */
interface Task {
    var state: RunningState?
    val steps: Set<Step>?
    val parameters: Set<Parameter>?
    var name: String?

    /**
     * Set to null the ID of the task.
     */
    fun resetId()

    /**
     * Changes the value of a task parameter. When a task parameter is changed, all linked step parameters are
     * updated too.
     *
     * @param parameterName Name of the parameter to update.
     * @param value The new value of the parameter.
     */
    fun updateParameter(parameterName: String, value: String) {
        parameters?.filter { it.name == parameterName }?.forEach { it.value = value }
    }

    /**
     * Returns the step of the task given its name.
     * **Note:** The steps of the same task shouldn't share the same name.
     *
     * @param name The step name.
     * @return The found step or null.
     */
    fun stepByName(name: String): Step? = steps?.first { it.name == name }


    /**
     * Returns the parameter of the task given its name.
     * **Note:** The parameters of the same task shouldn't share the same name.
     *
     * @param name The parameter name.
     * @return The found parameter or null.
     */
    fun parameterByName(name: String): Parameter? = parameters?.first { it.name == name }

}
