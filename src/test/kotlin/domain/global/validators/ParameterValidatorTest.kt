package domain.global.validators

import domain.processing.entities.objects.ParameterEntity
import org.junit.Assert.*

/**
 * Created by Christian Sperandio on 10/04/2016.
 */
class ParameterValidatorTest {
    @org.junit.Test
    fun validationWithError() {
        val param = ParameterEntity()
        val validation = domain.global.validators.validate(param)

        assertTrue(validation.hasError)
        assertNotNull(validation.errors["NO_NAME"])
    }

    @org.junit.Test
    fun validationWithNoError() {
        val param = ParameterEntity("A Param")
        val validation = domain.global.validators.validate(param)

        assertFalse(validation.hasError)
    }
}
