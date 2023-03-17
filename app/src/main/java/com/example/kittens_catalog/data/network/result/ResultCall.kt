package com.example.kittens_catalog.data.network.result

import com.squareup.moshi.Moshi
import com.x5.courierapp.data.network.result.Result
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultCall<T>(
    private val proxy: Call<T>,
    private val moshi: Moshi
) : Call<Result<T>> {
    override fun enqueue(callback: Callback<Result<T>>) {
        proxy.enqueue(ResultCallback(this, callback, moshi))
    }

    override fun clone(): Call<Result<T>> {
        return ResultCall(proxy.clone(), moshi)
    }

    override fun timeout(): Timeout {
        return proxy.timeout()
    }

    override fun execute(): Response<Result<T>> = throw NotImplementedError()

    override fun cancel() = proxy.cancel()

    override fun request(): Request = proxy.request()

    override fun isExecuted() = proxy.isExecuted

    override fun isCanceled() = proxy.isCanceled
}