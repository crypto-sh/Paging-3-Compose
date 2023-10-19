package com.vipaso.core.api


import com.vipaso.users.dto.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun users(
        @Query("q") query   : String = "",
        @Query("page") page : Int = 1,
        @Query("per_page") pageSize : Int = 25
    ) : BaseResponse

}