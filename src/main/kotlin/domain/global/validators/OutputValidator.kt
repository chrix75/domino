package domain.global.validators

import domain.processing.entities.objects.OutputEntity
import domain.processing.entities.objects.ParameterEntity

/**
 * Created by Christian Sperandio on 03/05/2016.
 *
 */
class OutputValidator : EntityValidator<OutputEntity> {
    private val _errors = mutableMapOf<String, Error>()

    override val errors: Map<String, Error>
        get() = _errors

    /**
     * Validate the provided output. All errors are saved into the errors property.
     *
     * @param e The parameter to validate.
     */
    override fun validate(e: OutputEntity) {
        e.name?.let {
            val _name = e.name!!
            if (_name.isEmpty() || _name.isBlank()) {
                _errors["NO_NAME"] = Error("NO_NAME", "An output must be named");
            }
        }
    }
}
