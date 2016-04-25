package domain.global.generators

import domain.global.entities.Entity
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

/**
 * The PropertyParameterGenerator objects generate a value from a properties file.
 *
 * Created by Christian Sperandio on 23/04/2016.
 *
 */
@NodeEntity
class PropertyParameterGenerator(@field:Property var propertiesPath: String? = null, @field:Property var propertyName: String? = null) : Entity(), ParameterGenerator {
    override fun resetId() {
        id = null
    }

    /**
     * Returns the value for the given key.
     *
     * @param v The property key to search.
     * @return The property value or an empty string if the key is not found.
     */
    override fun generateFrom(v: String): String {
        val props = Properties()

        try {
            props.load(FileInputStream(v))
            return props.getProperty(v, "")
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