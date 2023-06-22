package com.startup.shared.events.data.api

import com.startup.shared.events.data.dto.CreateEventDto
import com.startup.shared.events.data.dto.EventDto
import com.startup.shared.events.data.dto.EventsResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface EventsApi {

	@GET("api/events")
	suspend fun getAll(@Query("filter") filter: String, @Query("page") page: String): EventsResponseDto

	@POST("api/events")
	suspend fun create(@Body event: CreateEventDto)

	@GET("api/events/{id}")
	suspend fun get(@Path("id") id: String): EventDto

	@PUT("api/events/{id}")
	suspend fun edit(@Path("id") id: String, @Body event: CreateEventDto)

	@DELETE("api/events/{id}")
	suspend fun delete(@Path("id") id: String)
}