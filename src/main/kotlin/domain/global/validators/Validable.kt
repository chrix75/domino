package domain.global.validators

/**
 * The Validable interface must be implemented by all entity objects.
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
interface Validable<T> {
    fun buildValidator(): EntityValidator<T>
}
