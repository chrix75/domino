package domain.processing.entities

import domain.global.entities.NamedEntity
import domain.global.validators.EntityValidator
import domain.global.validators.TaskValidator
import domain.global.validators.Validable
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship
import org.neo4j.ogm.annotation.Transient

/**
 * The Task class is the entity representation of a task.
 * A task has at least one step.
 *
 * Only template task has a descripption.
 *
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
@NodeEntity
open class Task(_name: String = "") : NamedEntity(_name), Validable<Task> {

    @Relationship(type = "HAS_STEP")
    private var _steps = mutableSetOf<Step>()

    @Property
    var description = ""
        set(value) {}

    @Property
    var state = RunningState.TO_CONFIGURE

    @Transient
    var steps = setOf<Step>()
        get() = this._steps
        set(value) {
            _steps = value.toMutableSet()
            field = _steps
        }

    override fun toString(): String {
        return "Task $id [$name] \"$description\""
    }

    /**
     * Returns an instance of the validator that checks if a task is valid.
     */
    override fun buildValidator(): EntityValidator<Task> {
        return TaskValidator()
    }

    /**
     * Resets the id of the current task and resets the id of all tasks's steps.
     */
    override fun resetId() {
        id = null

        steps.forEach { it.resetId() }
    }

}
