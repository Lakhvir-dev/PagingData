package com.app.myapplication.apiResponseHandler.ui.displaydata

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.app.myapplication.R
import com.app.myapplication.apiResponseHandler.utils.LoadingFooter
import com.app.myapplication.apiResponseHandler.utils.ProgressDialog
import com.app.myapplication.databinding.ActivityDisplayDataBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DisplayDataActivity:AppCompatActivity() {
    private val viewModel: DisplayDataViewModel by viewModels()
    private lateinit var binding: ActivityDisplayDataBinding
    private lateinit var adapter: DataListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_data)
        setAdapter()
    }

    private fun setAdapter() {
        adapter = DataListAdapter()
        binding.rvList.adapter = adapter
        adapter.callBack={
            startActivity(Intent(this, DetailActivity::class.java).putExtra("model",Gson().toJson(it)))
        }

        binding.rvList.adapter = adapter.withLoadStateFooter(LoadingFooter {
            adapter.retry()
        })

        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    lifecycleScope.launch {
                        ProgressDialog.hideProgress()
                    }
                }

                is LoadState.Loading -> {
                    lifecycleScope.launch {
                        ProgressDialog.showProgress(this@DisplayDataActivity)
                    }
                }

                is LoadState.Error -> {
                    lifecycleScope.launch {
                        ProgressDialog.hideProgress()
                    }
                    (it.refresh as LoadState.Error).error.localizedMessage
                }
            }
        }

        loadUserLeads()
    }

    /**
     * load my UserLeads from network
     */
    private fun loadUserLeads() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getUserLeads().collectLatest {data->
                adapter.submitData(data)
            }
        }
    }
}
