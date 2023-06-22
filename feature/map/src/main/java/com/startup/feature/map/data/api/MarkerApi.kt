package com.startup.feature.map.data.api

import com.startup.feature.map.data.dto.MarkersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MarkerApi {

	@GET("api/markers")
	suspend fun get(@Query("filter") filter: String, @Query("page") page: String): MarkersResponseDto
}