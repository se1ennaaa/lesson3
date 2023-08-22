package com.example.lesson3.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson3.data.PlayList
import com.example.lesson3.databinding.ItemVideoBinding
import com.example.lesson3.loadImage

class PlayVideoAdapter: RecyclerView.Adapter<PlayVideoAdapter.PlayVideoViewHolder>() {

    private var list = mutableListOf<PlayList.Item>()

    fun addList(list: List<PlayList.Item>) {
        this.list = list as MutableList<PlayList.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayVideoViewHolder {
        return PlayVideoViewHolder(ItemVideoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PlayVideoViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class PlayVideoViewHolder(private val binding:ItemVideoBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PlayList.Item) {
            binding.imgView.loadImage(item.snippet.thumbnails.standard.url)
            binding.tvTitle.text = item.snippet.title
            binding.tvDesc.text = item.snippet.description
        }

    }
}