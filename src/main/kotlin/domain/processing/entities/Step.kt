package domain.processing.entities

import domain.processing.entities.objects.RunningState

/**
 * Created by batman on 20/04/2016.
 *
 */
interface Step {
    var state: RunningState?
    val steps: Set<Step>?
    val parameters: Set<Parameter>?

    fun resetId()

}