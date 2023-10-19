package com.vipaso.core

import android.content.Context
import com.vipaso.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.handleError(context: Context) : String {
    return if (isIntentConnectionIssue()) {
        context.getString(R.string.internet_connection_error)
    } else {
        when(this) {
            is HttpException -> {
                when(code()) {
                    in 500 .. 599 -> {
                        context.getString(R.string.server_exception)
                    }
                    in 400 .. 499 -> {
                        //TODO read the error body and provide the message
                        context.getString(R.string.invalid_request)
                    }
                    else -> {
                        message()
                    }
                }
            }
            else -> message ?: context.getString(R.string.unknown_error)
        }
    }

}

fun Throwable.isIntentConnectionIssue(): Boolean {
    return when (this) {
        is SocketTimeoutException,
        is UnknownHostException -> true
        else -> false
    }
}