package com.startup.ecoapp.di

import com.startup.ecoapp.App.Companion.BACKEND
import com.startup.ecoapp.core.network.interceptors.headerInterceptor
import com.startup.ecoapp.core.network.interceptors.loggingInterceptor
import com.startup.ecoapp.core.network.interceptors.noConnectionInterceptor
import com.startup.ecoapp.core.network.interceptors.tokenInterceptor
import com.startup.ecoapp.core.network.provider.provideMoshi
import com.startup.ecoapp.core.network.provider.provideOkHttpClient
import com.startup.ecoapp.core.network.provider.provideRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val LOG_INTERCEPTOR = "logInterceptor"
const val NO_CONNECT_INTERCEPTOR = "noConnectionInterceptor"
const val TOKEN_INTERCEPTOR = "tokenInterceptor"
const val HEADER_INTERCEPTOR = "headerInterceptor"
const val TOKEN_AUTHENTICATOR = "tokenAuthenticator"
const val ORIGINAL = "original"

val networkModule = module {
	single(named(LOG_INTERCEPTOR)) { loggingInterceptor() }
	single(named(NO_CONNECT_INTERCEPTOR)) { noConnectionInterceptor(androidContext()) }
	single(named(TOKEN_INTERCEPTOR)) { tokenInterceptor(get()) }
	single(named(HEADER_INTERCEPTOR)) { headerInterceptor() }

	single { provideMoshi() }

	single(named(ORIGINAL)) {
		provideOkHttpClient(
			logging = get(named(LOG_INTERCEPTOR)),
			noConnection = get(named(NO_CONNECT_INTERCEPTOR)),
			token = get(named(TOKEN_INTERCEPTOR)),
			header = get(named(HEADER_INTERCEPTOR)),
			tokenAuthenticator = get(named(TOKEN_AUTHENTICATOR))
		)
	}
	single(named(ORIGINAL)) {
		provideRetrofit(
			okHttpClient = get(named(ORIGINAL)),
			moshi = get(),
			url = getProperty(BACKEND)
		)
	}
}