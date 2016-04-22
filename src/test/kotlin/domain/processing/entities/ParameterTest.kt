package domain.processing.entities

import domain.processing.entities.objects.ParameterEntity
import domain.processing.entities.proxies.ParameterProxy
import org.junit.Assert

import org.junit.Before
import org.junit.Test

/**
 * Created by Christian Sperandio on 20/04/2016.
 *
 */
class ParameterTest {

    private lateinit var parameterEntity: ParameterEntity
    private lateinit var parameter: Parameter


    @Before
    fun setUp() {
        parameterEntity = ParameterEntity("A parameter")
        parameter = ParameterProxy(parameterEntity)
    }

    @Test
    fun setValue() {
        parameter.value = "a new value"
        Assert.assertEquals("a new value", parameter.value)
    }
}
