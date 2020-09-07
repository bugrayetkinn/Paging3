package com.yetkin.paging3.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */
interface GithubServiceAPI {

    @GET("search/repositories?sort=stars")
    suspend fun getData(
        @Query("q") query: String,
        @Query("page") page: Int,
    ): RepoSearchResponse
}