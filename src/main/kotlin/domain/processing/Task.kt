package domain.processing

import domain.processing.entities.objects.RunningState
import domain.processing.entities.objects.TaskEntity
import utils.createEntityDirectory
import java.io.File

/**
 * Created by Christian Sperandio on 20/04/2016.
 *
 *
 * @property state The current task state.
 * @property steps The steps of the task.
 * @property parameters The root task parameters used for step parameters initalization.
 * @property name Task name
 *
 */
class Task(val taskEntity: TaskEntity) {

    /**
     * Set to null the ID of the task.
     */
    fun resetId() {
        taskEntity.resetId()
    }

    var state: RunningState?
        get() = taskEntity.state
        set(value) {
            taskEntity.state = value
        }

    val steps: Set<Step>?
        get() = Step.convertStepEntitiesToSteps(taskEntity.steps)

    val parameters: Set<Parameter>?
        get() = Parameter.convertParameterEntitiesToParameters(taskEntity.parameters)

    var name: String?
        get() = taskEntity.name
        set(value) {
            taskEntity.name = value
        }

    /**
     * Creates folder for the task inside the `baseDir` directory.
     * The directories of (sub)steps are created and parameter generators are initialized too.
     *
     *
     * @param baseDir The directory in which the step folder will be created.
     * @throws NoSuchFileException
     * @throws java.nio.file.FileAlreadyExistsException
     * @throws IllegalStateException
     */
    fun prepareTaskDirectory(baseDir: File) {
        val _name = name
        val _id = taskEntity.id

        if (_name == null || _id == null)
            throw IllegalStateException("The system can't create a folder for a task with no name or no id.")

        val path = createEntityDirectory(baseDir, _name, _id)

        steps?.forEach { it.prepareStepDirectory(path) }
    }

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
     * Sets the given parameters for the current task.
     *
     * @param parameters An Pairs array of which each pair is <Parameter name, Parameter value>.
     */
    fun updateParameters(parameters: Array<Pair<String, String>>) {
        for (param in parameters) {
            updateParameter(param.first, param.second)
        }
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
