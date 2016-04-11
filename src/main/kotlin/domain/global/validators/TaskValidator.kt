package domain.global.validators

import domain.global.errors.Error
import domain.processing.entities.Task

/**
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class TaskValidator: EntityValidator<Task> {
    private val _errors = mutableMapOf<String, Error>()

    override val errors: Map<String, Error>
        get() = _errors

    override fun validate(e: Task) {
        if (e.name.isEmpty() || e.name.isBlank()) {
            _errors["NO_NAME"] = domain.global.errors.Error("NO_NAME", "A task must be named");
        }

        if (e.steps.size == 0) {
            _errors["NO_STEP"] = domain.global.errors.Error("NO_STEP", "A task must have at least one step");
        }

        e.steps.forEach { step ->
            val validation = domain.global.validators.validate(step)
            _errors.putAll(validation.errors)
        }
    }
}
