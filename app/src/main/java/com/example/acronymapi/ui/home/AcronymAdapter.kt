package com.example.acronymapi.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapi.databinding.HolderAcronymBinding
import com.example.acronymapi.network.AcronymResults

class AcronymAdapter: ListAdapter < AcronymResults, AcronymAdapter.AcronymHolder >(DiffCallBack) {
    class AcronymHolder(private val binding: HolderAcronymBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(acc: AcronymResults){
            binding.acronym = acc
        }
    }

    object DiffCallBack: DiffUtil.ItemCallback< AcronymResults >(){
        override fun areItemsTheSame(oldItem: AcronymResults, newItem: AcronymResults): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AcronymResults, newItem: AcronymResults): Boolean {
            return oldItem.lf == newItem.lf
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymHolder {
        return AcronymHolder(HolderAcronymBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AcronymHolder, position: Int) {
        val newAcc = getItem(position)
        holder.bind(getItem(position))
        holder.bind(newAcc)
    }
}
