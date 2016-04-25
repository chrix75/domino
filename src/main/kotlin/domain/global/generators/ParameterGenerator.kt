package domain.global.generators

/**
 * Interface implemented by classes calculate parameter value.
 *
 * The parameter generators are entity saved into DB. That explains why the interface implies the reset ID method.
 *
 *
 * Created by Christian Sperandio on 23/04/2016.
 *
 */
interface ParameterGenerator {

    /**
     * Generates a value from the given value.
     *
     * @param v
     * @return the parameter value
     */
    fun generateFrom(v: String) : String

    /**
     * Generate a value from internal data.
     *
     * @return the parameter value
     */
    fun generateFrom() : String

    /**
     * Sets to null the ID of parameter generator.
     */
    fun resetId()

}