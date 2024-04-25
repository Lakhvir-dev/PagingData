package com.app.myapplication.apiResponseHandler.ui.displaydata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.app.myapplication.apiResponseHandler.retrofit.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DisplayDataViewModel @Inject constructor(private val apiService: ApiService):ViewModel() {
    private var myUsersDataSource: DisplayDataPaginationSource? = null

    fun getUserLeads() = Pager(PagingConfig(pageSize = 20)) {
        DisplayDataPaginationSource(apiService).also {
            myUsersDataSource = it
        }
    }.flow.cachedIn(viewModelScope)

}