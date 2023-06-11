package com.startup.ecoapp.core.network.provider

import com.startup.ecoapp.core.network.interceptors.TokenAuthenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

fun provideOkHttpClient(
	logging: Interceptor,
	noConnection: Interceptor,
	token: Interceptor,
	header: Interceptor,
	tokenAuthenticator: TokenAuthenticator
): OkHttpClient =
	OkHttpClient().newBuilder()
		.authenticator(tokenAuthenticator)
		.addInterceptor(token)
		.addInterceptor(noConnection)
		.addInterceptor(logging)
		.addInterceptor(header)
		.applyDefaultSetups()
		.build()

fun provideRefreshOkHttpClient(
	logging: Interceptor,
	noConnection: Interceptor,
	token: Interceptor,
	header: Interceptor
): OkHttpClient =
	OkHttpClient().newBuilder()
		.addInterceptor(token)
		.addInterceptor(noConnection)
		.addInterceptor(logging)
		.addInterceptor(header)
		.applyDefaultSetups()
		.build()

private const val VALUE_TIMEOUT: Long = 60

private fun OkHttpClient.Builder.applyDefaultSetups(): OkHttpClient.Builder {
	connectTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	writeTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	readTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	hostnameVerifier { _, _ -> true }
	return this
}
