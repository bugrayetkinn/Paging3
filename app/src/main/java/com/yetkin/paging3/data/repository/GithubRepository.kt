package com.yetkin.paging3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yetkin.paging3.data.GithubPagingSource
import com.yetkin.paging3.data.remote.GithubServiceAPI
import com.yetkin.paging3.data.remote.Repo
import kotlinx.coroutines.flow.Flow

/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */

class GithubRepository(
    private val githubServiceAPI: GithubServiceAPI
) {

    fun getSearch(query: String): Flow<PagingData<Repo>> = Pager(
        PagingConfig(
            pageSize = 10
        )
    ) {
        GithubPagingSource(githubServiceAPI, query)
    }.flow
}