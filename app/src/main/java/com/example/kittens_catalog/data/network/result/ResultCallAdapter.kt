package com.example.kittens_catalog.data.network.result

import com.squareup.moshi.Moshi
import com.x5.courierapp.data.network.result.Result
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultCallAdapter<R>(
    private val type: Type,
    private val moshi: Moshi
) : CallAdapter<R, Call<Result<R>>> {
    override fun responseType() = type

    override fun adapt(call: Call<R>): Call<Result<R>> = ResultCall(call, moshi)
}
