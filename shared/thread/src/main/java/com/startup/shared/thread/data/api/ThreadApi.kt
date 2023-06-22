package com.startup.shared.thread.data.api

import com.startup.shared.thread.data.dto.EditThreadDto
import com.startup.shared.thread.data.dto.NewThreadDto
import com.startup.shared.thread.data.dto.ThreadDto
import com.startup.shared.thread.data.dto.ThreadResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ThreadApi {

	@GET("api/threads")
	suspend fun getAll(@Query("filter") filter: String, @Query("page") page: String): ThreadResponseDto

	@GET("api/threads/{id}")
	suspend fun get(@Path("id") id: String): ThreadDto

	@POST("api/threads")
	suspend fun create(@Body thread: NewThreadDto)

	@PUT("api/threads/{id}")
	suspend fun edit(@Path("id") id: String, @Body thread: EditThreadDto)

	@DELETE("api/threads/{id}")
	suspend fun delete(@Path("id") id: String)
}