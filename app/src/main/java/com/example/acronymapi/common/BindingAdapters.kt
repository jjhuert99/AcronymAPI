package com.example.acronymapi.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapi.network.AcronymResults
import com.example.acronymapi.ui.home.AcronymAdapter

@BindingAdapter("bindAccs")
fun bindAccs(recyclerView: RecyclerView, data: List<AcronymResults>?){
    val adapter = recyclerView.adapter as AcronymAdapter
    adapter.submitList(data)
}
