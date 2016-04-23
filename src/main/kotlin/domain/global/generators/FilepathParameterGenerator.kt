package domain.global.generators

import java.io.File
import java.util.*

/**
 * Created by Christian Sperandio on 23/04/2016.
 *
 */
class FilepathParameterGenerator : ParameterGenerator {
    override fun generateFrom1(directory: String): String {
        return "$directory${File.separator}${UUID.randomUUID()}"
    }
}