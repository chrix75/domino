package domain.global.entities

import org.neo4j.ogm.annotation.GraphId

/**
 * The basic class is shared by all other entities.
 * Created by batman on 09/04/2016.
 *
 */
abstract class Entity {

    @GraphId
    var id: Long? = null

    abstract fun resetId()
}