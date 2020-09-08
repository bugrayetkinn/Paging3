package com.yetkin.paging3.module

import com.yetkin.paging3.data.repository.GithubRepository
import org.koin.dsl.module


/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */

val githubRepositoryModule = module {

    single { GithubRepository(get()) }
}