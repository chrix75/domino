package domain.global.validators

import domain.global.validators.Error
import domain.processing.entities.Parameter

/**
 * Validator for parameter entities.
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class ParameterValidator : EntityValidator<Parameter> {
    private val _errors = mutableMapOf<String, Error>()

    override val errors: Map<String, Error>
        get() = _errors

    /**
     * Validate the provided parameter. All errors are saved into the errors property.
     *
     * @param e The parameter to validate.
     */
    override fun validate(e: Parameter) {
        if (e.name.isEmpty() || e.name.isBlank()) {
            _errors["NO_NAME"] = Error("NO_NAME", "A parameter must be named");
        }
    }
}
