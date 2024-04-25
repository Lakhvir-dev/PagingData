package com.app.myapplication.apiResponseHandler.ui.displaydata

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.myapplication.apiResponseHandler.retrofit.ApiService
import javax.inject.Inject


class DisplayDataPaginationSource @Inject constructor(private val apiService: ApiService) : PagingSource<Int, DisplayDataModel.DisplayDataModelItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DisplayDataModel.DisplayDataModelItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getData("https://jsonplaceholder.typicode.com/posts")
            val items = response.body()

            LoadResult.Page(
                data = items?: arrayListOf(),
                prevKey = null,
                nextKey = if ((items?.size ?: 0) >= LIMIT) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DisplayDataModel.DisplayDataModelItem>): Int {
        return 1
    }

    companion object {
        private const val LIMIT = 20
    }
}