package com.app.myapplication.apiResponseHandler.ui.displaydata

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.databinding.ItemListBinding


class DataListAdapter : PagingDataAdapter<DisplayDataModel.DisplayDataModelItem, DataListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<DisplayDataModel.DisplayDataModelItem>() {
        override fun areItemsTheSame(
            oldItem: DisplayDataModel.DisplayDataModelItem,
            newItem: DisplayDataModel.DisplayDataModelItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DisplayDataModel.DisplayDataModelItem,
            newItem: DisplayDataModel.DisplayDataModelItem
        ): Boolean {
            return oldItem == newItem
        }
    }) {

     var callBack:((DisplayDataModel.DisplayDataModelItem)->Unit)?=null
    inner class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                callBack?.invoke(snapshot().items[absoluteAdapterPosition])
            }
        }
    }
    

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=getItem(position)
        holder.binding.apply {
           item?.id.let { tvId.text="id :"+it.toString()}
           item?.userId.let { tvUserId.text="userId :"+it.toString()}
           item?.title.let { tvTitle.text="title :"+it.toString()}
           item?.body.let { tvBody.text="body :"+it.toString()}
        }
    }
}
