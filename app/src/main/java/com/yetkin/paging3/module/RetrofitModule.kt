package com.yetkin.paging3.module

import com.yetkin.paging3.data.remote.GithubServiceAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */

private const val BASE_URL = "https://api.github.com/"

val retrofitModule = module {

    single {
        val logger = HttpLoggingInterceptor()
        logger.level = Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubServiceAPI::class.java)
    }


}