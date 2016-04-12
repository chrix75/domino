package domain.processing.entities

/**
 *
 * Describes the possible state for tasks or steps.
 *
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
enum class RunningState {
    TO_CONFIGURE, READY, STARTING, RUNNING, STOPPING, STOPPED, ERROR, SUCCESS
}


