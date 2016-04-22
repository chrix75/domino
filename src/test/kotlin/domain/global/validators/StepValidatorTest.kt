package domain.global.validators

import domain.processing.entities.objects.ParameterEntity
import domain.processing.entities.objects.StepEntity
import org.junit.Assert.*

/**
 * Created by Christian Sperandio on 10/04/2016.
 */
class StepValidatorTest {
    @org.junit.Test
    fun validationWithError() {
        val step = StepEntity()
        val validation = domain.global.validators.validate(step)

        assertTrue(validation.hasError)
        assertNotNull(validation.errors["NO_NAME"])
        assertNotNull(validation.errors["NO_PARAMETER"])
    }

    @org.junit.Test
    fun validationWithoutError() {
        val step = StepEntity("A Step")
        step.parameters = setOf(ParameterEntity("P1"))

        val validation = domain.global.validators.validate(step)

        assertFalse(validation.hasError)
    }
}
