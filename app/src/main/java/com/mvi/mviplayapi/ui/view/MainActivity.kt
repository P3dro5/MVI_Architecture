package com.mvi.mviplayapi.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory
import com.mvi.mviplayapi.R
import com.mvi.mviplayapi.data.api.ApiHelperImpl
import com.mvi.mviplayapi.data.api.RetrofitBuilder
import com.mvi.mviplayapi.data.model.Game
import com.mvi.mviplayapi.databinding.ActivityMainBinding
import com.mvi.mviplayapi.ui.adapter.MainAdapter
import com.mvi.mviplayapi.ui.intent.GamesIntent
import com.mvi.mviplayapi.ui.viewmodel.MainViewModel
import com.mvi.mviplayapi.ui.viewstate.DataState
import com.mvi.mviplayapi.util.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity() {

    private lateinit var dataViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        binding.buttonShowGames.setOnClickListener {
            lifecycleScope.launch {
                dataViewModel.dataIntent.send(GamesIntent.FetchData)
            }
        }
    }


    private fun setupUI() {
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.run {
                addItemDecoration(
                    DividerItemDecoration(
                        binding.recyclerView.context,
                        (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
                    )
                )
            }
            binding.recyclerView.adapter = adapter
    }


    private fun setupViewModel() {
        dataViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        )[MainViewModel::class.java]
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            dataViewModel.dataState.collect {
                with(binding){
                    when (it) {
                        is DataState.Inactive -> {
                            Log.d("Inactive","Initial state")
                        }
                        is DataState.Loading -> {
                            buttonShowGames.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }

                        is DataState.ResponseData -> {
                            progressBar.visibility = View.GONE
                            buttonShowGames.visibility = View.GONE
                            renderList(it.data.results)
                        }
                        is DataState.ApiError -> {
                            progressBar.visibility = View.GONE
                            buttonShowGames.visibility = View.VISIBLE
                            Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(users: List<Game>) {
        with(binding){
            recyclerView.visibility = View.VISIBLE
            users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
            adapter.notifyDataSetChanged()
        }
    }
}