package domain.processing.services

import domain.global.errors.SaveException
import domain.processing.entities.Parameter
import domain.processing.entities.Step
import domain.processing.entities.Task
import neo4j.utils.Neo4jSessionFactory
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.neo4j.ogm.session.Neo4jSession
import org.neo4j.ogm.session.Session

/**
 * Created by batman on 10/04/2016.
 */
class TaskServiceTest {
    private lateinit var session: Session

    @Before
    fun setUp() {
        session = Neo4jSessionFactory.getNeo4jSession()
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
        val templates = taskService.findAll(Task::class.java).filter { it.name == "TU - 627B7892-8614-4C01-A4DE-FD86A0253E25 - Matching keys" }

        if (templates.size == 0) {
            fail("Test template not found")
        }

        try {
            taskService.saveFromTemplate(templates[0])
            fail("The system must not accept 2 task with the same name.")
        } catch(e: SaveException) {}
    }

    @Test
    fun successTaskCreationFromTemplate() {
        val taskService = TaskService(session)
        val templates = taskService.findAll(Task::class.java).filter { it.name == "TU - 627B7892-8614-4C01-A4DE-FD86A0253E25 - Matching keys" }

        if (templates.size == 0) {
            fail("Test template not found")
        }

        try {
            val newTask = templates[0]
            newTask.name = "TU - 627B7892-8614-4C01-A4DE-FD86A0253E25 - Matching keys REAL TASK"
            taskService.saveFromTemplate(newTask)

            taskService.delete(newTask)
        } catch(e: SaveException) {
            fail("The system must not accept 2 task with the same name.")
        }
    }
}