package domain.global.generators

import java.io.File
import java.util.*

/**
 * Created by Christian Sperandio on 23/04/2016.
 *
 */
class FilepathParameterGenerator : ParameterGenerator {
    private var directory: String? = null

    override fun generateFrom(v: String): String {
        return "$v${File.separator}${UUID.randomUUID()}"
    }

    override fun generateFrom(): String {
        val dir = directory
        dir?.let {
            return generateFrom(dir)
        }

        return ""
    }
}