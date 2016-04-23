package domain.processing.services

import domain.global.errors.SaveException
import domain.processing.entities.objects.TaskEntity
import domain.global.services.GenericCRUD
import domain.processing.entities.Task
import domain.processing.entities.objects.ParameterEntity
import domain.processing.entities.proxies.TaskProxy
import neo4j.utils.TransactionManager
import org.neo4j.ogm.session.Session
import java.util.*

/**
 * The TaskService class manages the tasks persistence.
 *
 * @constructor session The Neo$j session used by this service.
 *
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
class TaskService(session: Session): GenericCRUD<TaskEntity>(session) {

    private val stepService = StepService(session)

    override fun getEntityClass(): Class<TaskEntity> {
        return TaskEntity::class.java
    }

    /**
     *
     * @throws SaveException
     */
    fun save(task: TaskEntity): Long? {
        val validation = domain.global.validators.validate(task)

        if (validation.hasError) {
            throw SaveException("Failed to save the task $task", null, validation.errors)
        }

        try {
            super.save(task, FETCH_TYPE.EAGER)
            return task.id
        } catch (e: Exception) {
            throw SaveException("Failure while task save for $task", e, validation.errors)
        }
    }

    /**
     * Takes the configuration of a template and made it as a new task.
     *
     * IMPORTANT: The template is not a template anymore at the end of this method.
     *
     * @throws SaveException
     */
    fun saveFromTemplate(template: Task): Long? {
        if (template is TaskProxy)
            return save(template.taskEntity)

        if (template is TaskEntity)
            return save(template)

        throw SaveException("The task must be either an entity or a proxy")
    }

    fun findByTemplateUUID(templateUUID: String): Task? {
        val templateResults = session.query(TaskEntity::class.java,
                "match p=(t:Task {uuid: {uuid}})-[*]->() return t, nodes(p), rels(p)",
                mapOf("uuid" to templateUUID)).iterator()

        if (templateResults.hasNext()) {
            val template = templateResults.next()

            template.resetId()
            return TaskProxy(template)
        }

        return null
    }

    fun findByID(id: Long): TaskEntity? {
        val templateResults = session.query(TaskEntity::class.java,
                "match p=(t:Task)-[*]->() where id(t) = {id} return t, nodes(p), rels(p)",
                mapOf("id" to id)).iterator()

        if (templateResults.hasNext()) {
            val template = templateResults.next()
            return template
        }

        return null
    }

    /**
     *
     * @throws SaveException
     */
    override fun delete(o: TaskEntity) {
        var tx = TransactionManager(session)
        tx.beginTransaction()

        try {
            o.steps?.forEach { step ->
                stepService.delete(step)
            }

            super.delete(o)

            tx.commit()
        } catch (e: Exception) {
            tx.rollback()
            throw SaveException("Failure while task deletion for $o", e, mapOf())
        } finally {
            tx.close()
        }
    }
}
