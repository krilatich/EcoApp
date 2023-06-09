package com.startup.ecoapp.core.network.token.domain.repository

import com.startup.ecoapp.core.network.token.domain.model.AuthTokenPair

interface TokenRepository {

	fun save(authTokenPair: AuthTokenPair)

	fun load(): AuthTokenPair
}