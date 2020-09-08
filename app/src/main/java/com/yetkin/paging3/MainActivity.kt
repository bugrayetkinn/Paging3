package com.yetkin.paging3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yetkin.paging3.adapter.GithubAdapter
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
        loadData()
    }

    private fun initializeRecycler() {

        mainBinding.recyclerview.setHasFixedSize(true)
        mainBinding.recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = GithubAdapter()
        mainBinding.recyclerview.adapter = adapter
    }

    private fun loadData() {

        lifecycleScope.launch {
            githubViewModel.searchRepo("Android").collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}