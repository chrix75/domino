package domain.global.errors

/**
 * Created by batman on 09/04/2016.
 *
 */
class SaveException(val msg: String, val excpt: Throwable?, val errors: Map<String, Error>): Exception(msg, excpt)
