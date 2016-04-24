package domain.global.generators

import domain.global.entities.Entity
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import java.io.File
import java.util.*

/**
 * Created by Christian Sperandio on 23/04/2016.
 *
 */
@NodeEntity
class FilepathParameterGenerator(@field:Property var directory: String? = null) : Entity(), ParameterGenerator {
    override fun resetId() {
        id = null
    }

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