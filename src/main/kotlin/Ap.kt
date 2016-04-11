import domain.processing.entities.Task
import domain.global.services.GenericCRUD
import domain.processing.services.TaskService
import neo4j.utils.Neo4jSessionFactory

/**
 * Created by Christian Sperandio on 09/04/2016.
 *
 */

fun main(args: Array<String>) {
    println("Hello Doctor")

    val session = Neo4jSessionFactory.getNeo4jSession()
    val taskService = TaskService(session)
    val task =  taskService.find(5, GenericCRUD.FETCH_TYPE.LAZY)

    if (task != null)
        println(task)

    println("It is the end.")
}
