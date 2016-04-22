package domain.processing.entities.proxies

import domain.processing.entities.Parameter
import domain.processing.entities.Step
import domain.processing.entities.objects.RunningState
import domain.processing.entities.objects.StepEntity

/**
 * Created by batman on 20/04/2016.
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
}