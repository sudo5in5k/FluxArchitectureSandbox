package com.example.githubbrowserwithflux.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubbrowserwithflux.data.remote.entity.QiitaItem
import com.example.githubbrowserwithflux.databinding.ItemQiitaListBinding

class MainAdapter(private val parentLifecycleOwner: LifecycleOwner) : ListAdapter<QiitaItem, MainAdapter.ViewHolder>(DiffCallback()) {

    var onItemClicked: ((item: QiitaItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQiitaListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val qiitaItem = getItem(position)
        holder.binding.apply {
            item = qiitaItem
            root.setOnClickListener {
                onItemClicked?.invoke(qiitaItem)
            }
            lifecycleOwner = parentLifecycleOwner
            executePendingBindings()
        }
    }

    inner class ViewHolder(val binding: ItemQiitaListBinding) :
        RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<QiitaItem>() {
        override fun areItemsTheSame(oldItem: QiitaItem, newItem: QiitaItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QiitaItem, newItem: QiitaItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
}