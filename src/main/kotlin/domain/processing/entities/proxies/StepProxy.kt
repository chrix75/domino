package domain.processing.entities.proxies

import domain.global.generators.FilepathParameterGenerator
import domain.processing.entities.Parameter
import domain.processing.entities.Step
import domain.processing.entities.objects.RunningState
import domain.processing.entities.objects.StepEntity
import java.io.File

/**
 * Created by Christian Sperandio on 20/04/2016.
 *
 */
class StepProxy(val stepEntity: StepEntity) : Step {

    companion object {
        fun convertStepEntitiesToSteps(steps: Set<StepEntity>?): Set<Step> {
            val proxies: MutableSet<Step> = mutableSetOf()
            steps?.forEach {
                proxies.add(StepProxy(it))
            }

            return proxies
        }

    }

    override var state: RunningState?
        get() = stepEntity.state
        set(value) {
            stepEntity.state = value
        }

    override val steps: Set<Step>?
        get() = StepProxy.convertStepEntitiesToSteps(stepEntity.steps)

    override val parameters: Set<Parameter>?
        get() = ParameterProxy.convertParameterEntitiesToParameters(stepEntity.parameters)

    override fun resetId() {
        stepEntity.resetId()
    }

    override val name: String?
        get() = stepEntity.name

    override fun prepareStepDirectory(baseDir: File) {
        val _name = name
        val _id = stepEntity.id

        if (_name == null || _id == null)
            throw IllegalStateException("The system can't create a folder for a step with no name or no id.")

        val stepPath = utils.createEntityDirectory(baseDir, _name, _id)


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
