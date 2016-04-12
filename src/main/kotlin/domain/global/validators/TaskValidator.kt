package domain.global.validators

import domain.global.validators.Error
import domain.processing.entities.Task

/**
 * Validator for task entities.
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class TaskValidator: EntityValidator<Task> {
    private val _errors = mutableMapOf<String, Error>()

    override val errors: Map<String, Error>
        get() = _errors

    /**
     * Validate the provided task. All errors are saved into the errors property.
     *
     * @param e The task to validate.
     */
    override fun validate(e: Task) {
        if (e.name.isEmpty() || e.name.isBlank()) {
            _errors["NO_NAME"] = Error("NO_NAME", "A task must be named");
        }

        if (e.steps.size == 0) {
            _errors["NO_STEP"] = Error("NO_STEP", "A task must have at least one step");
        }

        e.steps.forEach { step ->
            val validation = domain.global.validators.validate(step)
            _errors.putAll(validation.errors)
        }
    }
}
