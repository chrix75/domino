package domain.processing.services

import domain.global.errors.SaveException
import domain.processing.entities.Parameter
import domain.processing.entities.Step
import domain.processing.entities.Task
import neo4j.utils.Neo4jSessionFactory
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.neo4j.ogm.session.Session
import java.util.*

/**
 * Created by Christian Sperandio on 10/04/2016.
 */
class TaskServiceTest {
    private lateinit var session: Session

    @Before
    fun setUp() {
        session = Neo4jSessionFactory.getNeo4jSession()
    }

    @After
    fun tearDown() {
        session.clear()
    }

    @Test
    fun saveWithSuccess() {
        val task = Task(this.javaClass.name + ": My Task")
        val step = Step("My First Step")
        step.parameters = setOf(Parameter("A Parameter"))
        task.steps = setOf<Step>(step)

        val taskService = TaskService(session)

        try {
            taskService.save(task)
            assertNotNull(task.id)
            taskService.delete(task)
        } catch (e: Exception) {
            e.printStackTrace()
            fail()
        }
    }

    @Test
    fun saveWithFailure() {
        val task = Task(this.javaClass.name + ": My Task")
        val step = Step("My First Step")
        task.steps = setOf<Step>(step)

        val taskService = TaskService(session)

        try {
            taskService.save(task)
            taskService.delete(task)
            fail()
        } catch (e: Exception) {}
    }

    /**
     * In this test below, the task creation fails because the task's name is not modified.
     */
    @Test
    fun failTaskCreationFromTemplate() {
        val taskService = TaskService(session)

        try {
            val template = taskService.findByTemplateUUID("5b922e85-8695-4cae-9ac6-1f7346f3426a")
            assertNotNull(template)

            val id = taskService.saveFromTemplate(template!!)
            assertNull(id)
        } catch(e: SaveException) {
        }
    }
    /**
     * In this test below, the task creation fails the template UUID is not found
     */
    @Test
    fun failTaskCreationFromUnknownTemplate() {
        val taskService = TaskService(session)

        try {
            val template = taskService.findByTemplateUUID("unknown template")
            assertNull(template)
        } catch(e: SaveException) {
        }
    }

    @Test
    fun successTaskCreationFromTemplate() {
        val taskService = TaskService(session)

        try {
            val template = taskService.findByTemplateUUID("5b922e85-8695-4cae-9ac6-1f7346f3426a")
            assertNotNull(template)

            template?.name = template?.name + " [" + UUID.randomUUID() + "]"
            val id = taskService.saveFromTemplate(template!!)

            assertNotNull(id)
        } catch(e: SaveException) {
            fail("The system must not accept 2 task with the same name.")
        }
    }
}
