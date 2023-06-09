package com.startup.ecoapp.core.network.interceptors

import com.startup.ecoapp.core.network.token.domain.usecase.RefreshTokensUseCase
import com.startup.ecoapp.core.network.token.domain.usecase.SaveTokenUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import kotlin.coroutines.CoroutineContext

class TokenAuthenticator(
	private val saveTokenUseCase: SaveTokenUseCase,
	private val refreshTokensUseCase: RefreshTokensUseCase
) : Authenticator, CoroutineScope {

	// Автоматически вызывается, когда сервер возвращает код ответа 401
	override fun authenticate(route: Route?, response: Response): Request? {
		// Обновляем токен при первой попытке
		if (responseCount(response) == 2) {
			val tokenPair = runBlocking { refreshTokensUseCase() }
			// TODO("По хорошему заменить на launch")
			saveTokenUseCase(tokenPair)

			return response.request
				.newBuilder()
				.header("Authorization", "Bearer ${tokenPair.accessToken}")
				.build()
		}
		return null
	}

	// Считает количество попыток обработки ответа 401
	private fun responseCount(response: Response): Int {
		var count = 1
		while (response.priorResponse != null) {
			count++
		}
		return count
	}

	private val job = Job()

	override val coroutineContext: CoroutineContext
		get() = Dispatchers.IO + job
}