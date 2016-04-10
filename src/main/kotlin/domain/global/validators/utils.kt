package domain.global.validators

/**
 * Created by batman on 10/04/2016.
 *
 */

fun <T> validate(e: Validable<T>): EntityValidator<T> {
     val validator = e.buildValidator()
    validator.validate(e as T)
    return validator
}