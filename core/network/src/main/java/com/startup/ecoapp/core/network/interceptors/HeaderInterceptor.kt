package com.startup.ecoapp.core.network.interceptors

import okhttp3.Interceptor

fun headerInterceptor(): Interceptor {
	return Interceptor { chain ->

		val request = chain.request()
			.newBuilder()
			.addHeader("Accept", "application/json")
			.addHeader("Content-Type", "application/json")
			.build()
		chain.proceed(request)
	}
}