package com.smile.myhome.data.net.exception

import com.past3.ketro.api.ApiErrorHandler
import retrofit2.Response

class ApiException : ApiErrorHandler() {
    override fun getExceptionType(response: Response<*>): Exception {
        return when (response.code()) {
            in 400..500 -> BadRequestException()
            else -> Exception("Unknown error")
        }
    }

    companion object ErrorConfig {
        class BadRequestException : Exception() {
            override val message = "Bad request"
        }
    }
}