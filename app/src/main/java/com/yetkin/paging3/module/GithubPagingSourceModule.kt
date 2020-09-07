package com.yetkin.paging3.module

import com.yetkin.paging3.data.GithubPagingSource
import org.koin.dsl.module


/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */
val githubPagingSourceModule = module {

    single { GithubPagingSource(get(), get()) }
}