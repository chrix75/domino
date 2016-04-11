package domain.global.validators

import domain.global.errors.Error
import domain.processing.entities.Step

/**
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class StepValidator: EntityValidator<Step> {
    private val _errors = mutableMapOf<String, Error>()

    override val errors: Map<String, Error>
        get() = _errors

    override fun validate(e: Step) {
        if (e.name.isEmpty() || e.name.isBlank()) {
            _errors["NO_NAME"] = domain.global.errors.Error("NO_NAME", "A step must be named");
        }

        if (e.parameters.size == 0) {
            _errors["NO_PARAMETER"] = domain.global.errors.Error("NO_PARAMETER", "A step must have at least one parameter");
        }

        e.parameters.forEach { param ->
            val validation = domain.global.validators.validate(param)
            _errors.putAll(validation.errors)
        }

        e.steps.forEach { substep ->
            val validation = domain.global.validators.validate(substep)
            _errors.putAll(validation.errors)
        }
    }
}
