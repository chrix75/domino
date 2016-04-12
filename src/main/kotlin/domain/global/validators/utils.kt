package domain.global.validators

/**
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */

/**
 * The generic validation function.
 *
 * @param e The entity to validate (must implement the Validable interface)
 * @return The validator of the provided entity.
 */
fun <T> validate(e: Validable<T>): EntityValidator<T> {
     val validator = e.buildValidator()
    validator.validate(e as T)
    return validator
}
