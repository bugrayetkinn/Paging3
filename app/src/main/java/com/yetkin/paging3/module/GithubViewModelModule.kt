package com.yetkin.paging3.module

import com.yetkin.paging3.GithubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */

val githubViewModelModule = module {

    viewModel { GithubViewModel(get()) }
}