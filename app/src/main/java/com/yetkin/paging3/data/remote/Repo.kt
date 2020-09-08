package com.yetkin.paging3.data.remote

import com.google.gson.annotations.SerializedName


/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */
data class Repo(

    @field:SerializedName("_id") val _repoId: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("full_name") val fullName: String,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("html_url") val url: String,
    @field:SerializedName("stargazers_count") val stars: Int,
    @field:SerializedName("forks_count") val forks: Int,
    @field:SerializedName("language") val language: String?
)