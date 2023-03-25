package com.x5.courierapp.data.network.result

sealed class Result<out T>(val isSuccess: Boolean) {
    abstract val value: T?

    abstract fun <R> map(mapper: (T) -> R): Result<R>
    object Loading : Result<Nothing>(isSuccess = false) {
        override val value: Nothing
            get() = throw UnsupportedOperationException()

        override fun <R> map(mapper: (Nothing) -> R): Result<R> =
            throw UnsupportedOperationException()

        override fun toString() = "Loading"
    }
    data class Success<T>(override val value: T) : Result<T>(isSuccess = true) {
        override fun <R> map(mapper: (T) -> R): Result<R> = Success(
            value = mapper.invoke(value)
        )

        override fun toString() = "Success - $value"
    }
    object Empty : Result<Nothing>(isSuccess = true) {
        override val value: Nothing?
            get() = null

        override fun <R> map(mapper: (Nothing) -> R): Result<R> = this

        override fun toString() = "Success"
    }
    data class Fail<out T>(
        val exception: Throwable? = null,
        val statusCode: Int = 400,
        val code: String = "fail",
        val message: String = "lol failed ahaha",
        val errorData: Any? = null,
        override val value: T? = null
    ) : Result<T>(isSuccess = false) {
        override fun <R> map(mapper: (T) -> R): Result<R> = Fail(
            exception, statusCode, code, message, value?.let(mapper)
        )
        override fun toString() = """
            ------------ HTTP ERROR ------------
            exception: $exception
            at line: ${exception?.toString()}
            status: $statusCode
            message: $message
            ------------------------------------
        """.trimIndent()
        // ????????
        companion object
    }

    data class NetworkError<out T>(
        val throwable: Throwable? = null,
        override val value: T? = null,
        val url: String? = null
    ) : Result<T>(isSuccess = false) {
        override fun <R> map(mapper: (T) -> R): Result<R> = NetworkError(
            throwable, value?.let(mapper)
        )
    }
}