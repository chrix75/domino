package actions

import kotlin.reflect.KClass

/**
 * Created by batman on 19/04/2016.
 *
 */
object ActionsDirectory {
    private val actionsClasses: Set<KClass<out Action>> = setOf(NumberAction::class, SortAction::class)

    val actions: Map<String, Action> = buildDirectory()

    private fun buildDirectory(): MutableMap<String, Action> {
        val actions = mutableMapOf<String, Action>()

        for (c in actionsClasses) {
            val action = c.constructors.iterator().next().call()
            actions[action.getActionId()] = action
        }

        return actions
    }
}