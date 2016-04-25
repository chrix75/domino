package domain.processing.entities.proxies


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

        val step = StepProxy(stepEntity)

        val expectedDirectory = File("/tmp/step_my_step_1")
        if (expectedDirectory.exists())
            expectedDirectory.deleteRecursively()

        assertFalse(expectedDirectory.exists())

        step.prepareStepDirectory(File("/tmp"))
        assertTrue(expectedDirectory.exists())
        assertTrue(expectedDirectory.isDirectory)
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