package domain.processing.entities

import domain.processing.entities.objects.RunningState
import java.io.File

/**
 * Created by Christian Sperandio on 20/04/2016.
 *
 * Interface for the (sub)steps of tasks.
 *
 * @property state Current state of the step.
 * @property steps Substeps.
 * @property parameters Parameters of the step.
 * @property name Step name.
 *
 */
interface Step {
    var state: RunningState?
    val steps: Set<Step>?
    val parameters: Set<Parameter>?
    val name: String?

    /**
     * Sets to null the ID of the current step.
     */
    fun resetId()

    /**
     * Returns the substep with the given name.
     * **Note:** Each substep should have an unique name.
     *
     * @param name
     * @return The substep or null if no step has this name.
     */
    fun stepByName(name: String): Step? = steps?.first { it.name == name }

    /**
     * Returns the parameter with the given name.
     * **Note:** Each parameter for a step should have an unique name.
     *
     * @param name
     * @return The parameter or null if none has this name.
     */
    fun parameterByName(name: String): Parameter? = parameters?.first { it.name == name }

    /**
     * Creates folder for the task inside the `baseDir` directory.
     *
     * @param baseDir The directory in which the step folder will be created.
     * @throws NoSuchFileException
     * @throws java.nio.file.FileAlreadyExistsException
     * @throws IllegalStateException
     */
    fun prepareStepDirectory(baseDir: File)

}
