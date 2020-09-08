package com.yetkin.paging3.data

import androidx.paging.PagingSource
import com.yetkin.paging3.data.remote.GithubServiceAPI
import com.yetkin.paging3.data.remote.Repo
import retrofit2.HttpException

/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */

class GithubPagingSource(
    private val githubServiceAPI: GithubServiceAPI,
    private val query: String
) : PagingSource<Int, Repo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {

        return try {
            val nextPageNumber = params.key ?: 1
            val response = githubServiceAPI.getData(query, nextPageNumber)
            LoadResult.Page(
                data = response.items,
                prevKey = nextPageNumber - 1,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}