package domain.processing.entities.proxies


import domain.global.generators.FilepathParameterGenerator
import domain.processing.entities.objects.ParameterEntity
import domain.processing.entities.objects.StepEntity
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.io.File

/**
 * Created by Christian Sperandio on 25/04/2016.
 */
class StepProxyTest {
    @Before
    fun setUp() {

    }

    @Test
    fun prepareStepDirectory() {
        val stepEntity = StepEntity("my step")
        stepEntity.id = 1

        val parameter = ParameterEntity("aParam")
        parameter.generator = FilepathParameterGenerator()
        stepEntity.parameters = setOf(parameter)

        val subStepEntity = StepEntity("a substep")
        subStepEntity.id = 2



        stepEntity.steps = setOf(subStepEntity)

        val step = StepProxy(stepEntity)

        val expectedDirectory = File("/tmp/step_my_step_1")
        if (expectedDirectory.exists())
            expectedDirectory.deleteRecursively()

        val anotherDirectory = File("/tmp/step_a_substep_2")
        if (anotherDirectory.exists())
            anotherDirectory.deleteRecursively()

        assertFalse(expectedDirectory.exists())
        assertFalse(anotherDirectory.exists())

        step.prepareStepDirectory(File("/tmp"))
        assertTrue(expectedDirectory.exists())
        assertTrue(expectedDirectory.isDirectory)


        assertTrue(anotherDirectory.exists())
        assertTrue(anotherDirectory.isDirectory)

        val generatedFilepath = stepEntity.parameters?.first()?.generator?.generateFrom()
        assertNotNull(generatedFilepath)
        assertTrue(generatedFilepath!!.startsWith("/tmp/step_my_step_1/"))
    }

    @Test
    fun failPrepareStepDirectory() {
        val stepEntity = StepEntity()
        val step = StepProxy(stepEntity)

        try {
            step.prepareStepDirectory(File("/tmp"))
            fail("WTF! A step with no name or no id can't have a directory")
        } catch (e:Exception) {

        }
    }

}