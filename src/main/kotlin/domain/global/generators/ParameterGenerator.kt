package domain.global.generators

/**
 * Created by Christian Sperandio on 23/04/2016.
 *
 */
interface ParameterGenerator {
    fun generateFrom(v: String) : String {
        return ""
    }

    fun generateFrom() : String {
        return ""
    }

    fun resetId()

}