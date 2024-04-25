package com.app.myapplication.apiResponseHandler.ui.displaydata


import com.google.gson.annotations.SerializedName

class DisplayDataModel : ArrayList<DisplayDataModel.DisplayDataModelItem>() {
    data class DisplayDataModelItem(
        @SerializedName("body")
        val body: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("userId")
        val userId: Int
    )
}