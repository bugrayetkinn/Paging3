package com.yetkin.paging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yetkin.paging3.databinding.RetryCardBinding


/**

Created by : Buğra Yetkin

Mail : bugrayetkinn@gmail.com

 */
class ReposLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ReposLoadStateAdapter.ReposLoadStateHolder>() {


    class ReposLoadStateHolder(
        private val retryCardBinding: RetryCardBinding,
        retry: () -> Unit
    ) :
        RecyclerView.ViewHolder(retryCardBinding.root) {


        init {
            retryCardBinding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                retryCardBinding.errorMsg.text = loadState.error.localizedMessage
            }

            retryCardBinding.progressBar.isVisible = loadState is LoadState.Loading
            retryCardBinding.retryButton.isVisible = loadState !is LoadState.Loading
            retryCardBinding.errorMsg.isVisible = loadState !is LoadState.Loading

        }
    }

    override fun onBindViewHolder(holder: ReposLoadStateHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ReposLoadStateHolder =
        ReposLoadStateHolder(RetryCardBinding.inflate(LayoutInflater.from(parent.context)), retry)
}