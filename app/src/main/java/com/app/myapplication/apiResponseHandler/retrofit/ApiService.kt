package com.app.myapplication.apiResponseHandler.retrofit

import com.app.myapplication.apiResponseHandler.ui.displaydata.DisplayDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getData(
        @Url url: String
    ): Response<DisplayDataModel>
}