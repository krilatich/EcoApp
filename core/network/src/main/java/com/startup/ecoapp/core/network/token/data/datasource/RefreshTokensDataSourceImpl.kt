package com.startup.ecoapp.core.network.token.data.datasource

import com.startup.ecoapp.core.network.token.data.api.RefreshTokensApi
import com.startup.ecoapp.core.network.token.data.mapper.toEntity

class RefreshTokensDataSourceImpl(private val api: RefreshTokensApi) : RefreshTokensDataSource {

	override suspend fun refresh() = api.refresh().toEntity()
}