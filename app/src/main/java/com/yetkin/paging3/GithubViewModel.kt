package com.yetkin.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yetkin.paging3.data.GithubPagingSource
import com.yetkin.paging3.data.remote.GithubServiceAPI
import com.yetkin.paging3.data.remote.Repo
import kotlinx.coroutines.flow.Flow


/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */
class GithubViewModel(
    private val githubServiceAPI: GithubServiceAPI,
) : ViewModel() {

    fun flow(query: String): Flow<PagingData<Repo>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        GithubPagingSource(githubServiceAPI, query)
    }.flow.cachedIn(viewModelScope)
}