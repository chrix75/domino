package domain.global.errors

/**
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
class SaveException(val msg: String, val excpt: Throwable?, val errors: Map<String, Error>): Exception(msg, excpt)
