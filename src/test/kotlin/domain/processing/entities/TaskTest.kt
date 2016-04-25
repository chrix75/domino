package domain.processing.entities

import domain.processing.Task
import domain.processing.services.TaskService
import neo4j.utils.Neo4jSessionFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Christian Sperandio on 22/04/2016.
 *
 */
class TaskTest {
    private var task: Task? = null

    @Before
    fun setUp() {
        val taskService = TaskService(Neo4jSessionFactory.getNeo4jSession())
        task = taskService.findByTemplateUUID("3364e06b-fc61-4c6f-8e1a-5b39bba31409")
        assertNotNull(task)
    }

    @Test
    fun updateParameter() {
        task?.updateParameter("PARAM 1", "/value/for/param1")

        val taskParameterValue = task?.parameterByName("PARAM 1")?.value
        assertEquals("/value/for/param1", taskParameterValue)

        val step2 = task?.stepByName("STEP 2")
        val step3 = step2?.stepByName("STEP 3")
        val step3ParameterValue = step3?.parameterByName("PARAM 3")?.value

        assertEquals(taskParameterValue, step3ParameterValue)
    }

}
