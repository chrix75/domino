package domain.processing.entities.proxies

import com.google.common.base.CaseFormat
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
    override var state: RunningState?
        get() = stepEntity.state
        set(value) {
            stepEntity.state = value
        }

    override val steps: Set<Step>?
        get() = stepEntity.steps

    override val parameters: Set<Parameter>?
        get() = stepEntity.parameters

    override fun resetId() {
        stepEntity.resetId()
    }

    override val name: String?
        get() = stepEntity.name

    override fun prepareStepDirectory(baseDir: File) {
        if (!baseDir.exists())
            throw NoSuchFileException(baseDir, null, "Directory ${baseDir.absolutePath} not found")

        val stepName = name
        val stepId = stepEntity.id

        if (stepName == null || stepId == null)
            throw IllegalStateException("The system can't create a folder for a step with no name or no id.")

        val stepDirName = stepName.replace("\\W", "_")
        val stepPath = "${baseDir}${File.pathSeparatorChar}step_${stepDirName}_$stepId"
        val stepDir = File(stepPath)

        if (!stepDir.exists()) {
            if (!stepDir.mkdir())
                throw IllegalStateException("Impossible to create the directory ${stepDir.absolutePath}")
        } else if (stepDir.isFile) {
            throw IllegalStateException("The directory ${stepDir.absolutePath} leads to a file")
        }

    }
}
