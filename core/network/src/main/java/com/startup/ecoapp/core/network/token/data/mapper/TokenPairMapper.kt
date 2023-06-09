package com.startup.ecoapp.core.network.token.data.mapper

import com.startup.ecoapp.core.network.token.data.dto.AuthTokenPairDto
import com.startup.ecoapp.core.network.token.domain.model.AuthTokenPair

fun AuthTokenPairDto.toEntity() = AuthTokenPair(accessToken = accessToken, refreshToken = refreshToken)