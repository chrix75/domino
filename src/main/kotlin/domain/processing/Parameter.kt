package domain.processing

import domain.processing.entities.objects.ParameterEntity

/**
 * Created by Christian Sperandio on 20/04/2016.
 *
 * Interface for task or step parameters.
 *
 * @property value The value of the parameter.
 * @property parameters The parameters are initialized by the current parameter.
 * @property name The name of the parameter.
 *
 */
class Parameter(val parameterEntity: ParameterEntity) {

    companion object {
        fun convertParameterEntitiesToParameters(parameters: Set<ParameterEntity>?): Set<Parameter> {
            val proxies: MutableSet<Parameter> = mutableSetOf()

            parameters?.forEach {
                proxies.add(Parameter(it))
            }

            return proxies
        }

    }

    val name: String?
        get() = parameterEntity.name

    /**
     * Set the parameter ID to null.
     */
    fun resetId() {
        parameterEntity.resetId()
    }

    var value: String?
        get() = parameterEntity.value
        set(value) { parameterEntity.value = value}

    val parameters: Set<Parameter>
        get() {
            val paramProxies: MutableSet<Parameter> = mutableSetOf()
            for (parameter in parameterEntity.parameters) {
                paramProxies.add(Parameter(parameter))
            }

            return paramProxies
        }
}
