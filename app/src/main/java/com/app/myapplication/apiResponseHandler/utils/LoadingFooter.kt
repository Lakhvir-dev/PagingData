package com.app.myapplication.apiResponseHandler.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.databinding.ItemLoadStateBinding

class LoadingFooter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadingFooter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoadStateViewHolder(ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context),parent,false), retry)

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)

    class LoadStateViewHolder(
        private val binding: ItemLoadStateBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
           binding.progressCircular.isVisible=loadState is LoadState.Loading
        }
    }
}