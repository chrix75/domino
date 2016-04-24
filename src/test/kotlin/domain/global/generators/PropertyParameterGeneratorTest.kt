package domain.global.generators

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Christian Sperandio on 23/04/2016.
 */
class PropertyParameterGeneratorTest {
    private lateinit var propertiesFilepath: String
    private lateinit var generator: ParameterGenerator

    @Before
    fun setUp() {
        propertiesFilepath = this.javaClass.getResource("/for_test.properties").path
        generator = PropertyParameterGenerator(propertiesFilepath, "a.property.for.generator")
    }

    @Test
    fun generateFrom2() {
        assertEquals("Property Generator", generator.generateFrom())
    }
}