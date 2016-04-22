package domain.processing.entities

import domain.processing.entities.objects.RunningState

/**
 * Created by batman on 20/04/2016.
 *
 */
interface Task {
    fun resetId()

    var state: RunningState?
    val steps: Set<Step>?
    val parameters: Set<Parameter>?
}