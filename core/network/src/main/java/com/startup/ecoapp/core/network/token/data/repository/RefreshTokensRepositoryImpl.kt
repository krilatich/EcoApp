package com.startup.ecoapp.core.network.token.data.repository

import com.startup.ecoapp.core.network.token.data.datasource.RefreshTokensDataSource
import com.startup.ecoapp.core.network.token.domain.repository.RefreshTokensRepository

class RefreshTokensRepositoryImpl(
	private val dataSource: RefreshTokensDataSource
) : RefreshTokensRepository {

	override suspend fun refreshTokens() = dataSource.refresh()
}