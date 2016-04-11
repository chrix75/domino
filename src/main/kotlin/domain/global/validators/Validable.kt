package domain.global.validators

/**
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
interface Validable<T> {
    fun buildValidator(): EntityValidator<T>
}
