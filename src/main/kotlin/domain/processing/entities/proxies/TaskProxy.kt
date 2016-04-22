package domain.processing.entities.proxies

import domain.processing.entities.Parameter
import domain.processing.entities.Step
import domain.processing.entities.Task
import domain.processing.entities.objects.RunningState
import domain.processing.entities.objects.TaskEntity

/**
 * Created by batman on 20/04/2016.
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
        get() = taskEntity.steps

    override val parameters: Set<Parameter>?
        get() = taskEntity.parameters
}