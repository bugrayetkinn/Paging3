package com.yetkin.paging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yetkin.paging3.data.remote.Repo
import com.yetkin.paging3.databinding.GithubCardBinding

/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */

class GithubAdapter : PagingDataAdapter<Repo, GithubAdapter.GithubViewHolder>(DiffUtilCallBack) {


    class GithubViewHolder(private val githubCardBinding: GithubCardBinding) :
        RecyclerView.ViewHolder(githubCardBinding.root) {

        fun bind(repo: Repo) {
            githubCardBinding.apply {
                txt1.text = repo.language
                txt2.text = "${repo.forks}"
            }
        }

    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder =
        GithubViewHolder(GithubCardBinding.inflate(LayoutInflater.from(parent.context)))

    companion object {

        val DiffUtilCallBack = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem
        }
    }
}