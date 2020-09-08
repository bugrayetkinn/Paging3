package com.yetkin.paging3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.yetkin.paging3.adapter.GithubAdapter
import com.yetkin.paging3.adapter.ReposLoadStateAdapter
import com.yetkin.paging3.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val githubViewModel: GithubViewModel by viewModel()
    private lateinit var githubAdapter: GithubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        initializeRecycler()
        mainBinding.buttonSearch.setOnClickListener {
            updateRepoListFromInput()
        }
    }

    private fun initializeRecycler() {


        mainBinding.apply {

            recyclerview.setHasFixedSize(true)
            recyclerview.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            githubAdapter = GithubAdapter()
            recyclerview.adapter = githubAdapter
            recyclerview.adapter = githubAdapter.withLoadStateHeaderAndFooter(
                header = ReposLoadStateAdapter { githubAdapter.retry() },
                footer = ReposLoadStateAdapter { githubAdapter.retry() }
            )

            githubAdapter.addLoadStateListener { loadState ->

                recyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error
            }
            btnRetry.setOnClickListener {
                githubAdapter.retry()
            }
        }
    }

    private fun loadData(query: String) {

        lifecycleScope.launch {
            githubViewModel.searchRepo(query).collectLatest { pagingData ->
                githubAdapter.submitData(pagingData)
            }
        }
    }

    private fun updateRepoListFromInput() {
        mainBinding.editTxtSearchRepo.text.trim().let {
            if (it.isNotEmpty()) {
                mainBinding.recyclerview.scrollToPosition(0)
                loadData(it.toString())
            }
        }
    }
}