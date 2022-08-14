package com.partyeer.util.functional

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
fun <T> Type.getGenericType(genericClass: Class<*>): Class<T> {
    return when (this) {
        is ParameterizedType -> {
            actualTypeArguments.find {
                genericClass.isAssignableFrom(it as Class<*>)
            } as Class<T>
        }
        else -> {
            val genericSuperClass = (this as Class<*>).genericSuperclass
            genericSuperClass!!.getGenericType(genericClass)
        }
    }
}