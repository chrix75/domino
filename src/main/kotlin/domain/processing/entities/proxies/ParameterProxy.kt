package domain.processing.entities.proxies

import domain.processing.entities.Parameter
import domain.processing.entities.objects.ParameterEntity

/**
 * Created by batman on 20/04/2016.
 *
 */
class ParameterProxy(val parameterEntity: ParameterEntity) : Parameter {
    override fun resetId() {
        parameterEntity.resetId()
    }

    override var value: String
        get() = parameterEntity.value
        set(value) { parameterEntity.value = value}

    override val parameters: Set<Parameter>
        get() {
            val paramProxies: MutableSet<Parameter> = mutableSetOf()
            for (parameter in parameterEntity.parameters) {
                paramProxies.add(ParameterProxy(parameter))
            }

            return paramProxies
        }
}