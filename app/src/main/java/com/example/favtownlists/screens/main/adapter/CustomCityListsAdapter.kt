package com.example.favtownlists.screens.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.favtownlists.R
import com.example.favtownlists.databinding.ListItemBinding
import com.example.favtownlists.repository.room.model.CityListInfoModel

class CustomCityListsAdapter(
    private val onItemClicked: (v: View, viewType: Int) -> Unit,
//    private val onHeaderClicked: (View) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var customCityLists: List<CityListInfoModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val diffCallback = CityListInfoDiffUtil()
    private val differ = AsyncListDiffer(this, diffCallback)

    inner class ListVH(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cityListInfo: CityListInfoModel) {
            binding.apply {
                listIcon.setColorFilter(cityListInfo.color)
                root.setOnClickListener { v ->
                    onItemClicked(v, TYPE_ITEM)
                }
            }
        }
    }

    inner class HeaderVH(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                listIcon.setImageDrawable(
                    itemView.context.resources.getDrawable(R.drawable.ic_baseline_add_circle_outline_24)
                )
                root.setOnClickListener { v ->
                    onItemClicked(v, TYPE_HEADER)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding =
                    ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderVH(binding)
            }
            else -> {
                val binding =
                    ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ListVH(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ListVH) {
            val cityListInfo = differ.currentList[position - 1]
            holder.bind(cityListInfo)
        } else if (holder is HeaderVH) {
            holder.setIsRecyclable(false)
            holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return customCityLists.size + 1
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }
}