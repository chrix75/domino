package domain.global.errors

import domain.global.validators.Error

/**
 * Created by Christian Sperandio on 09/04/2016.
 *
 */
class SaveException(val msg: String, val excpt: Throwable?, val errors: Map<String, Error>): Exception(msg, excpt) {
    constructor(_message: String) : this(_message, null, mapOf())
}
