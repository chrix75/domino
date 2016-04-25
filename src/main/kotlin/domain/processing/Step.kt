package domain.processing

import domain.global.generators.FilepathParameterGenerator
import domain.processing.entities.objects.RunningState
import domain.processing.entities.objects.StepEntity
import utils.createEntityDirectory
import java.io.File

/**
 * Created by Christian Sperandio on 20/04/2016.
 *
 */
class Step(val stepEntity: StepEntity) {

    companion object {
        fun convertStepEntitiesToSteps(steps: Set<StepEntity>?): Set<Step> {
            val proxies: MutableSet<Step> = mutableSetOf()
            steps?.forEach {
                proxies.add(Step(it))
            }

            return proxies
        }

    }

    var state: RunningState?
        get() = stepEntity.state
        set(value) {
            stepEntity.state = value
        }

    val steps: Set<Step>?
        get() = convertStepEntitiesToSteps(stepEntity.steps)

    val parameters: Set<Parameter>?
        get() = Parameter.convertParameterEntitiesToParameters(stepEntity.parameters)

    /**
     * Sets to null the ID of the current step.
     */
    fun resetId() {
        stepEntity.resetId()
    }

    val name: String?
        get() = stepEntity.name


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
     * Creates folder for the step inside the `baseDir` directory.
     * The directories of sub steps are created and parameter generators are initialized too.
     *
     *
     * @param baseDir The directory in which the step folder will be created.
     * @throws NoSuchFileException
     * @throws java.nio.file.FileAlreadyExistsException
     * @throws IllegalStateException
     */
    fun prepareStepDirectory(baseDir: File) {
        val _name = name
        val _id = stepEntity.id

        if (_name == null || _id == null)
            throw IllegalStateException("The system can't create a folder for a step with no name or no id.")

        val stepPath = createEntityDirectory(baseDir, _name, _id)


        // initializes the generators if needed
        stepEntity.parameters?.forEach { param ->
            val generator = param.generator
            generator?.let {
                if (generator is FilepathParameterGenerator) {
                    generator.directory = stepPath.absolutePath
                }
            }
        }

        // prepares the sub steps
        steps?.forEach { it.prepareStepDirectory(baseDir) }
    }
}
