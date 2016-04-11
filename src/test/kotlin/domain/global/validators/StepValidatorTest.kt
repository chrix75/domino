package domain.global.validators

import domain.processing.entities.Parameter
import domain.processing.entities.Step
import org.junit.Assert.*

/**
 * Created by Christian Sperandio on 10/04/2016.
 */
class StepValidatorTest {
    @org.junit.Test
    fun validationWithError() {
        val step = Step()
        val validation = domain.global.validators.validate(step)

        assertTrue(validation.hasError)
        assertNotNull(validation.errors["NO_NAME"])
        assertNotNull(validation.errors["NO_PARAMETER"])
    }

    @org.junit.Test
    fun validationWithoutError() {
        val step = Step("A Step")
        step.parameters = setOf(Parameter("P1"))

        val validation = domain.global.validators.validate(step)

        assertFalse(validation.hasError)
    }
}
