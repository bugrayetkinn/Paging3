package com.yetkin.paging3

import android.app.Application
import com.yetkin.paging3.module.githubPagingSourceModule
import com.yetkin.paging3.module.githubRepositoryModule
import com.yetkin.paging3.module.githubViewModelModule
import com.yetkin.paging3.module.retrofitModule
import org.koin.core.context.startKoin


/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                retrofitModule,
                githubPagingSourceModule,
                githubViewModelModule,
                githubRepositoryModule,
            )
        }
    }
}