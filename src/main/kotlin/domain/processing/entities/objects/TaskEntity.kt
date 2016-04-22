package domain.processing.entities.objects

import domain.global.entities.NamedEntity
import domain.global.validators.EntityValidator
import domain.global.validators.TaskValidator
import domain.global.validators.Validable
import domain.processing.entities.Parameter
import domain.processing.entities.Step
import domain.processing.entities.Task
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
@NodeEntity(label = "Task")
class TaskEntity(_name: String = "") : NamedEntity(_name), Validable<TaskEntity>, Task {

    @Property
    var description = ""

    @Property
    override var state: RunningState? = RunningState.TO_CONFIGURE


    @Relationship(type = "HAS_PARAMETER", direction = Relationship.OUTGOING)
    override var parameters: Set<ParameterEntity>? = null

    @Relationship(type = "HAS_STEP", direction = Relationship.OUTGOING)
    override var steps: Set<StepEntity>? = null

    override fun toString(): String {
        return "Task $id [$name] \"$description\""
    }

    /**
     * Returns an instance of the validator that checks if a task is valid.
     */
    override fun buildValidator(): EntityValidator<TaskEntity> {
        return TaskValidator()
    }

    /**
     * Resets the id of the current task and resets the id of all tasks's steps.
     */
    override fun resetId() {
        id = null

        parameters?.forEach { it.resetId() }

        steps?.forEach { it.resetId() }
    }

}
