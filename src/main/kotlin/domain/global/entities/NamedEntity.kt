package domain.global.entities

import domain.global.entities.Entity
import org.neo4j.ogm.annotation.Property

/**
 * All entities having a name must extends this class.
 *
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
abstract class NamedEntity(@field:Property var name: String? = "") : Entity()
