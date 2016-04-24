package domain.global.generators

import java.io.FileInputStream
import java.io.InputStream
import java.util.*

/**
 * Created by Christian Sperandio on 23/04/2016.
 *
 */
class PropertyParameterGenerator(var propertiesPath: String? = null, var propertyName: String? = null) : ParameterGenerator {
    override fun generateFrom(otherPropertyName: String): String {
        val props = Properties()

        try {
            props.load(FileInputStream(propertiesPath))
            return props.getProperty(otherPropertyName, "")
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    override fun generateFrom(): String {
        val name = propertyName
        name?.let {
            return generateFrom(name)
        }

        return ""
    }
}