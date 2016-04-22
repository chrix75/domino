package domain.global.validators

import domain.global.validators.Error
import domain.processing.entities.objects.StepEntity

/**
 * Validator for step entities.
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class StepValidator: EntityValidator<StepEntity> {
    private val _errors = mutableMapOf<String, Error>()

    override val errors: Map<String, Error>
        get() = _errors

    /**
     * Validate the provided step. All errors are saved into the errors property.
     *
     * @param e The step to validate.
     */
    override fun validate(e: StepEntity) {
        e.name?.let {
            val _name = e.name!!

            if (_name.isEmpty() || _name.isBlank()) {
                _errors["NO_NAME"] = Error("NO_NAME", "A step must be named");
            }
        }
        if (e.parameters == null || e.parameters?.size == 0) {
            _errors["NO_PARAMETER"] = Error("NO_PARAMETER", "A step must have at least one parameter");
        }

        e.parameters?.forEach { param ->
            val validation = domain.global.validators.validate(param)
            _errors.putAll(validation.errors)
        }

        e.steps?.forEach { substep ->
            val validation = domain.global.validators.validate(substep)
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
