package domain.global.generators

import java.io.FileInputStream
import java.io.InputStream
import java.util.*

/**
 * Created by Christian Sperandio on 23/04/2016.
 *
 */
class PropertyParameterGenerator : ParameterGenerator {
    override fun generateFrom2(propertiesFilepath: String, propertyName: String): String {
        val props = Properties()

        try {
            props.load(FileInputStream(propertiesFilepath))
            return props.getProperty(propertyName, "")
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }
}