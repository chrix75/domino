package domain.global.validators

import domain.global.validators.Error
import domain.processing.entities.objects.TaskEntity

/**
 * Validator for task entities.
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class TaskValidator: EntityValidator<TaskEntity> {
    private val _errors = mutableMapOf<String, Error>()

    override val errors: Map<String, Error>
        get() = _errors

    /**
     * Validate the provided task. All errors are saved into the errors property.
     *
     * @param e The task to validate.
     */
    override fun validate(e: TaskEntity) {
        e.name?.let {
            val _name: String = e.name!!
            if (_name.isEmpty() || _name.isBlank()) {
                _errors["NO_NAME"] = Error("NO_NAME", "A task must be named");
            }

            if (e.steps == null || e.steps?.size == 0) {
                _errors["NO_STEP"] = Error("NO_STEP", "A task must have at least one step");
            }
        }

        e.steps?.forEach { step ->
            val validation = domain.global.validators.validate(step)
            _errors.putAll(validation.errors)
        }

        // checks unique names of steps
        val uniqueStepNames = e.steps?.map { it.name }?.toSet()
        if (uniqueStepNames?.size != e.steps?.size)
            _errors["DUPLICATE_NAMES"] = Error("DUPLICATE_NAMES", "At least two steps have the same name")

        // checks unique names of parameters
        val uniqueParamNames = e.parameters?.map { it.name }?.toSet()
        if (uniqueParamNames?.size != e.parameters?.size)
            _errors["DUPLICATE_NAMES"] = Error("DUPLICATE_NAMES", "At least two parameters have the same name")


    }
}
