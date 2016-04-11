package domain.global.validators

import domain.global.errors.Error
import domain.processing.entities.Parameter

/**
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class ParameterValidator : EntityValidator<Parameter> {
    private val _errors = mutableMapOf<String, Error>()

    override val errors: Map<String, Error>
        get() = _errors

    override fun validate(e: Parameter) {
        if (e.name.isEmpty() || e.name.isBlank()) {
            _errors["NO_NAME"] = domain.global.errors.Error("NO_NAME", "A parameter must be named");
        }
    }
}
