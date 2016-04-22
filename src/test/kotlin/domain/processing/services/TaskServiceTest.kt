package domain.processing.services

import domain.global.errors.SaveException
import domain.processing.entities.objects.ParameterEntity
import domain.processing.entities.objects.StepEntity
import domain.processing.entities.objects.TaskEntity
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
        println("Session=" + session)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun saveWithSuccess() {
        val task = TaskEntity(this.javaClass.name + ": My Task")
        val step = StepEntity("My First Step")
        step.parameters = setOf(ParameterEntity("A Parameter"))
        task.steps = setOf<StepEntity>(step)

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
        val task = TaskEntity(this.javaClass.name + ": My Task")
        val step = StepEntity("My First Step")
        task.steps = setOf<StepEntity>(step)

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
            val template = taskService.findByTemplateUUID("0f4a77f60b-ba74-435e-9f78-7ab8dcaa5a31")
            assertNull(template)
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
            val task = taskService.findByTemplateUUID("0f4af60b-ba74-435e-9f78-7ab8dcaa5a31")
            assertNotNull(task)

            task?.name = task?.name + " [" + UUID.randomUUID() + "]"
            task?.updateParameter("firstInputFilename", "/the/first/file")

            val id = taskService.saveFromTemplate(task!!)

            assertNotNull(id)
        } catch(e: SaveException) {
            e.printStackTrace()
            fail("The system must not accept 2 task with the same name.")
        }
    }
}
