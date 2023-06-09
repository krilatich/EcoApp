package com.startup.ecoapp.core.network.utils

import android.util.Log
import com.startup.ecoapp.core.network.interceptors.NoConnectivityException
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

@Suppress("FunctionName")
inline fun CoroutineNetworkExceptionHandler(
	crossinline handler: (code: Int) -> Unit,
): CoroutineExceptionHandler =
	object : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {
		override fun handleException(context: CoroutineContext, exception: Throwable) {
			handler.invoke(exception.toNetworkException().code)
		}
	}

/**
 * Маппер для ошибки из ретрофита
 **/
fun Throwable.toNetworkException(): NetworkException {
	Log.e("NetworkException", this.message ?: this.toString())
	val code = when (this) {
		is NoConnectivityException -> NetworkCode.INTERNET_CONNECTION_ERROR
		is HttpException           -> code() // код из HttpException
		else                       -> NetworkCode.UNKNOWN
	}
	return NetworkException(message = message, code = code)
}