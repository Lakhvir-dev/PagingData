package com.app.myapplication.apiResponseHandler.ui.displaydata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.myapplication.R
import com.app.myapplication.databinding.ActivityDetailsBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity:AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.toolbar.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        getIntentData()
    }
     private fun getIntentData(){
            if (intent.hasExtra("model")){
                val data=intent.getStringExtra("model")
                val item= Gson().fromJson(data, DisplayDataModel.DisplayDataModelItem::class.java)
                binding.layoutDetails.apply {
                    item?.id.let { tvId.text=getString(R.string.id).plus(it.toString())}
                    item?.userId.let { tvUserId.text=getString(R.string.userid).plus(it.toString())}
                    item?.title.let { tvTitle.text=getString(R.string.title).plus(it.toString())}
                    item?.body.let { tvBody.text=getString(R.string.body).plus(it.toString())}
                }
            }
        }
}