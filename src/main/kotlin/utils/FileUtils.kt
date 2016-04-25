package utils

import java.io.File

/**
 * Created by Christian Sperandio on 25/04/2016.
 *
 */

fun createEntityDirectory(baseDir: File, entityName: String, entityId: Long): File {
    if (!baseDir.exists())
        throw NoSuchFileException(baseDir, null, "Directory ${baseDir.absolutePath} not found")

    val entityDirName = entityName.replace(Regex("[^0-9a-zA-Z]"), "_")
    val entityPath = "${baseDir}${File.separator}step_${entityDirName}_$entityId"
    val entityDir = File(entityPath)

    if (!entityDir.exists()) {
        if (!entityDir.mkdir())
            throw IllegalStateException("Impossible to create the directory ${entityDir.absolutePath}")
    } else if (entityDir.isFile) {
        throw FileAlreadyExistsException(entityDir, null, "The directory ${entityDir.absolutePath} leads to a file")
    }

    return entityDir

}