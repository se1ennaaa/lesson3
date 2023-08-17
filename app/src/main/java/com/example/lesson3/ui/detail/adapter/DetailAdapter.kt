package com.example.lesson3.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson3.data.PlayList
import com.example.lesson3.data.PlaylistItem
import com.example.lesson3.databinding.ItemDetailBinding
import com.example.lesson3.loadImage

class DetailAdapter (private val onClick: (PlaylistItem.Item) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private var list = mutableListOf<PlaylistItem.Item>()

    fun addList(list: List<PlaylistItem.Item>) {
        this.list = list as MutableList<PlaylistItem.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class DetailViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PlaylistItem.Item) {
            binding.tvTime.text = item.kind ?: "Пусто"
            binding.tvVideoName.text = item.snippet.title ?: "Пусто"
            binding.ivVideo.loadImage(item.snippet.thumbnails.standard.url!!) ?: "Пусто"
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }
}