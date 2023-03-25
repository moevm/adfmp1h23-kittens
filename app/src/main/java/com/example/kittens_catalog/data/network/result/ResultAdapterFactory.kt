package com.example.kittens_catalog.data.network.result

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultAdapterFactory @Inject constructor(private val moshi: Moshi) : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val innerType = getInnerType(returnType)
        val rawInnerType = innerType?.let { getRawType(it) }

        return when {
            rawInnerType != Result::class.java -> null
            innerType is ParameterizedType -> {
                val resultInnerType = getParameterUpperBound(0, innerType)
                ResultCallAdapter<Any?>(resultInnerType, moshi)
            }
            else -> ResultCallAdapter<Nothing>(Nothing::class.java, moshi)
        }
    }
    private fun getInnerType(returnType: Type): Type? {
        val rawReturnType: Class<*> = getRawType(returnType)
        return if (rawReturnType == Call::class.java && returnType is ParameterizedType) {
            val callInnerType: Type = getParameterUpperBound(0, returnType)
            callInnerType
        } else {
            null
        }
    }
}