package com.yetkin.paging3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yetkin.paging3.data.remote.Repo
import com.yetkin.paging3.data.repository.GithubRepository
import kotlinx.coroutines.flow.Flow


/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */
class GithubViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Repo>>? = null

    fun searchRepo(query: String): Flow<PagingData<Repo>> {

        val lastResult = currentSearchResult

        if (query == currentQueryValue && lastResult != null) {
            return lastResult
        }

        currentQueryValue = query
        val newResult: Flow<PagingData<Repo>> =
            githubRepository.getSearch(query).cachedIn(viewModelScope)

        currentSearchResult = newResult
        return newResult
    }


}