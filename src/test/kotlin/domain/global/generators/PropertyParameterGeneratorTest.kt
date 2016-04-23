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
        generator = PropertyParameterGenerator()
        propertiesFilepath = this.javaClass.getResource("/for_test.properties").path
    }

    @Test
    fun generateFrom2() {
        assertEquals("Property Generator", generator.generateFrom2(propertiesFilepath, "a.property.for.generator"))
    }
}