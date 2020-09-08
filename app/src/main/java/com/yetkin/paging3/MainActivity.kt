package com.yetkin.paging3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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
    private lateinit var adapter: GithubAdapter

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
            adapter = GithubAdapter()
            recyclerview.adapter = adapter
            recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
                header = ReposLoadStateAdapter { adapter.retry() },
                footer = ReposLoadStateAdapter { adapter.retry() }
            )
        }
    }

    private fun loadData(query: String) {

        lifecycleScope.launch {
            githubViewModel.searchRepo(query).collectLatest { pagingData ->
                adapter.submitData(pagingData)
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