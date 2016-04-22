package domain.global.validators

import domain.processing.entities.objects.ParameterEntity
import domain.processing.entities.objects.StepEntity
import domain.processing.entities.objects.TaskEntity
import org.junit.Assert.*

/**
 * Created by Christian Sperandio on 10/04/2016.
 */
class TaskValidatorTest {
    @org.junit.Test
    fun validationWithError() {
        val task = TaskEntity()
        val validation = domain.global.validators.validate(task)

        assertTrue(validation.hasError)
        assertNotNull(validation.errors["NO_NAME"])
        assertNotNull(validation.errors["NO_STEP"])
    }

    @org.junit.Test
    fun validationWithNestedError() {
        val task = TaskEntity("My Task")
        task.steps = setOf<StepEntity>(StepEntity("My First Step"))

        val validation = domain.global.validators.validate(task)

        assertTrue(validation.hasError)
        assertNotNull(validation.errors["NO_PARAMETER"])
    }

    @org.junit.Test
    fun validationWithNoError() {
        val task = TaskEntity("My Task")
        val step = StepEntity("My First Step")
        step.parameters = setOf(ParameterEntity("A Parameter"))

        task.steps = setOf<StepEntity>(step)

        val validation = domain.global.validators.validate(task)

        assertFalse(validation.hasError)
    }
}
