package com.example.lesson3.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.example.lesson3.data.PlayList
import com.example.lesson3.databinding.ItemPlaylistBinding

class PlayListAdapter(val onClick:(PlayList.Item)->Unit) : Adapter<PlayListAdapter.PlayListViewHolder>() {

    private var list = mutableListOf<PlayList.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(lists: List<PlayList.Item>) {
        this.list = lists as MutableList<PlayList.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayListViewHolder {
        return PlayListViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int =
        list.size


    inner class PlayListViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(item: PlayList.Item) {
            binding.ivPlaylistImage.load(item.snippet.thumbnails.default.url)
            binding.namePlaylist.text = item.snippet.title
            binding.tvAmountVideo.text = item.contentDetails.itemCount.toString() + " video series"
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }
}