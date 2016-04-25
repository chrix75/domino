package domain.processing.entities.proxies

import domain.processing.entities.Parameter
import domain.processing.entities.Step
import domain.processing.entities.Task
import domain.processing.entities.objects.RunningState
import domain.processing.entities.objects.TaskEntity
import java.io.File

/**
 * Created by Christian Sperandio on 20/04/2016.
 *
 */
class TaskProxy(val taskEntity: TaskEntity) : Task {
    override fun resetId() {
        taskEntity.resetId()
    }

    override var state: RunningState?
        get() = taskEntity.state
        set(value) {
            taskEntity.state = value
        }

    override val steps: Set<Step>?
        get() = StepProxy.convertStepEntitiesToSteps(taskEntity.steps)

    override val parameters: Set<Parameter>?
        get() = ParameterProxy.convertParameterEntitiesToParameters(taskEntity.parameters)

    override var name: String?
        get() = taskEntity.name
        set(value) {
            taskEntity.name = value
        }

    override fun prepareTaskDirectory(baseDir: File) {
        val _name = name
        val _id = taskEntity.id

        if (_name == null || _id == null)
            throw IllegalStateException("The system can't create a folder for a task with no name or no id.")

        val path = utils.createEntityDirectory(baseDir, _name, _id)

        steps?.forEach { it.prepareStepDirectory(path) }
    }
}
