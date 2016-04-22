package domain.global.validators

import domain.global.validators.Error
import domain.processing.entities.objects.ParameterEntity

/**
 * Validator for parameter entities.
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class ParameterValidator : EntityValidator<ParameterEntity> {
    private val _errors = mutableMapOf<String, Error>()

    override val errors: Map<String, Error>
        get() = _errors

    /**
     * Validate the provided parameter. All errors are saved into the errors property.
     *
     * @param e The parameter to validate.
     */
    override fun validate(e: ParameterEntity) {
        e.name?.let {
            val _name = e.name!!
            if (_name.isEmpty() || _name.isBlank()) {
                _errors["NO_NAME"] = Error("NO_NAME", "A parameter must be named");
            }
        }
    }
}
