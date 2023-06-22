package com.startup.ecoapp.di

import com.startup.ecoapp.App.Companion.BACKEND
import com.startup.ecoapp.core.network.createRetrofitService
import com.startup.ecoapp.core.network.interceptors.TokenAuthenticator
import com.startup.ecoapp.core.network.interceptors.refreshTokenInterceptor
import com.startup.ecoapp.core.network.provider.provideRefreshOkHttpClient
import com.startup.ecoapp.core.network.provider.provideRetrofit
import com.startup.ecoapp.core.network.token.data.api.RefreshTokensApi
import com.startup.ecoapp.core.network.token.data.datasource.RefreshTokensDataSource
import com.startup.ecoapp.core.network.token.data.datasource.RefreshTokensDataSourceImpl
import com.startup.ecoapp.core.network.token.data.repository.RefreshTokensRepositoryImpl
import com.startup.ecoapp.core.network.token.data.repository.TokenRepositoryImpl
import com.startup.ecoapp.core.network.token.data.storage.SharedPrefsDataStorage
import com.startup.ecoapp.core.network.token.data.storage.TokenDataStorage
import com.startup.ecoapp.core.network.token.domain.repository.RefreshTokensRepository
import com.startup.ecoapp.core.network.token.domain.repository.TokenRepository
import com.startup.ecoapp.core.network.token.domain.usecase.GetUserIdUseCase
import com.startup.ecoapp.core.network.token.domain.usecase.LoadTokenUseCase
import com.startup.ecoapp.core.network.token.domain.usecase.RefreshTokensUseCase
import com.startup.ecoapp.core.network.token.domain.usecase.SaveTokenUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val REFRESH_TOKEN_OKHTTP_CLIENT = "refreshTokenOkhttpClient"
const val REFRESH_TOKEN_RETROFIT_CLIENT = "refreshTokenRetrofitClient"
const val REFRESH_TOKEN_INTERCEPTOR = "refreshTokenInterceptor"

val tokenModule = module {

	single(named(REFRESH_TOKEN_INTERCEPTOR)) { refreshTokenInterceptor(get()) }

	single(named(REFRESH_TOKEN_OKHTTP_CLIENT)) {
		provideRefreshOkHttpClient(
			logging = get(named(LOG_INTERCEPTOR)),
			noConnection = get(named(NO_CONNECT_INTERCEPTOR)),
			header = get(named(HEADER_INTERCEPTOR)),
			token = get(named(REFRESH_TOKEN_INTERCEPTOR))
		)
	}
	single(named(REFRESH_TOKEN_RETROFIT_CLIENT)) {
		provideRetrofit(
			okHttpClient = get(named(REFRESH_TOKEN_OKHTTP_CLIENT)),
			moshi = get(),
			url = getProperty(BACKEND)
		)
	}

	single(named(TOKEN_AUTHENTICATOR)) {
		TokenAuthenticator(
			saveTokenUseCase = get(),
			refreshTokensUseCase = get()
		)
	}

	single { createRetrofitService<RefreshTokensApi>(get(named(REFRESH_TOKEN_RETROFIT_CLIENT))) }

	factory<RefreshTokensDataSource> { RefreshTokensDataSourceImpl(get()) }
	single<TokenDataStorage> { SharedPrefsDataStorage(get()) }

	single<TokenRepository> { TokenRepositoryImpl(get()) }
	single<RefreshTokensRepository> { RefreshTokensRepositoryImpl(get()) }

	single { SaveTokenUseCase(get()) }
	single { LoadTokenUseCase(get()) }
	single { RefreshTokensUseCase(get()) }
	single { GetUserIdUseCase(get()) }
}