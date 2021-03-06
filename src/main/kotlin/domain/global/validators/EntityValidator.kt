package domain.global.validators

import domain.global.validators.Error

/**
 * Interface implemented by all entity validators.
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
interface EntityValidator<T> {
    val errors: Map<String, Error>

    val hasError: Boolean
        get() = errors.size > 0

    fun validate(e: T)
}
