package com.example.kittens_catalog.data.network.result

import com.squareup.moshi.Moshi
import com.x5.courierapp.data.network.result.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ResultCallback<T>(
    private val proxy: ResultCall<T>,
    private val callback: Callback<Result<T>>,
    private val moshi: Moshi
) : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        val result = if (response.isSuccessful) {
            response.body()
                ?.let { Result.Success(it) }
                ?: run { Result.Empty }
        } else {
            Result.Fail(exception = Exception("message:${response.message()}; statusCode:${response.code()}"))
        }
        callback.onResponse(proxy, Response.success(result))
    }
    override fun onFailure(call: Call<T>, error: Throwable) {
        val url = call.request().url().toString()
        val result: Result<T> = when (error) {
            is IOException -> {
                Result.NetworkError(throwable = Exception(error.message), url = url)
            }
            else -> Result.Fail(exception = error)
        }
        callback.onResponse(proxy, Response.success(result))
    }
}