package domain.global.validators

import domain.processing.entities.Parameter
import domain.processing.entities.Step
import domain.processing.entities.Task
import org.junit.Assert.*

/**
 * Created by batman on 10/04/2016.
 */
class TaskValidatorTest {
    @org.junit.Test
    fun validationWithError() {
        val task = Task()
        val validation = domain.global.validators.validate(task)

        assertTrue(validation.hasError)
        assertNotNull(validation.errors["NO_NAME"])
        assertNotNull(validation.errors["NO_STEP"])
    }

    @org.junit.Test
    fun validationWithNestedError() {
        val task = Task("My Task")
        task.steps = setOf<Step>(Step("My First Step"))

        val validation = domain.global.validators.validate(task)

        assertTrue(validation.hasError)
        assertNotNull(validation.errors["NO_PARAMETER"])
    }

    @org.junit.Test
    fun validationWithNoError() {
        val task = Task("My Task")
        val step = Step("My First Step")
        step.parameters = setOf(Parameter("A Parameter"))

        task.steps = setOf<Step>(step)

        val validation = domain.global.validators.validate(task)

        assertFalse(validation.hasError)
    }
}