package domain.global.generators

import domain.global.entities.Entity
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

/**
 * Created by Christian Sperandio on 23/04/2016.
 *
 */
@NodeEntity
class PropertyParameterGenerator(@field:Property var propertiesPath: String? = null, @field:Property var propertyName: String? = null) : Entity(), ParameterGenerator {
    override fun resetId() {
        id = null
    }

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