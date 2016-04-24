package neo4j.utils

import org.neo4j.ogm.session.Session
import org.neo4j.ogm.session.SessionFactory

/**
 * The util singleton manages the Neo4J session.
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
object Neo4jSessionFactory  {
    val sessionFactory: SessionFactory = SessionFactory("domain.global.entities", "domain.processing.entities", "domain.global.generators")

    fun getNeo4jSession(): Session {
        return sessionFactory.openSession()
    }
}
