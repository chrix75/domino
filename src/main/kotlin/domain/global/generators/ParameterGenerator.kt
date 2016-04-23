package domain.global.generators

/**
 * Created by Christian Sperandio on 23/04/2016.
 *
 */
interface ParameterGenerator {
    fun generateFrom1(v: String) : String {
        return ""
    }

    fun generateFrom2(v1: String, v2: String) : String {
        return ""
    }

    fun generateFromN(vs: Array<String>) : String {
        return ""
    }

}