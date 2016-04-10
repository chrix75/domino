package domain.global.validators

import domain.processing.entities.Parameter
import org.junit.Assert.*

/**
 * Created by batman on 10/04/2016.
 */
class ParameterValidatorTest {
    @org.junit.Test
    fun validationWithError() {
        val param = Parameter()
        val validation = domain.global.validators.validate(param)

        assertTrue(validation.hasError)
        assertNotNull(validation.errors["NO_NAME"])
    }

    @org.junit.Test
    fun validationWithNoError() {
        val param = Parameter("A Param")
        val validation = domain.global.validators.validate(param)

        assertFalse(validation.hasError)
    }
}