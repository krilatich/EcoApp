package com.startup.ecoapp.core.network.token.data.datasource

import com.startup.ecoapp.core.network.token.domain.model.AuthTokenPair

interface RefreshTokensDataSource {

	suspend fun refresh(): AuthTokenPair
}