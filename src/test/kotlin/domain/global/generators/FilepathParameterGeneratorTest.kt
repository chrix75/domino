package domain.global.generators

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Christian Sperandio on 23/04/2016.
 */
class FilepathParameterGeneratorTest {
    @Test
    fun generateFrom1() {
        val generator = FilepathParameterGenerator("/my/folder/")
        assertTrue(generator.generateFrom().startsWith("/my/folder/"))
    }

}