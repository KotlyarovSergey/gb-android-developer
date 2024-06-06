package com.ksv.hw18permission.presentation

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ksv.hw18permission.databinding.PhotoViewItemBinding
import com.ksv.hw18permission.entity.PhotoItem

class PhotoAdapter() : RecyclerView.Adapter<PhotoViewHolder>() {
    private var data: List<PhotoItem> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            PhotoViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoItem = data.getOrNull(position)
        with(holder.binding) {
            dateTextView.text = photoItem?.date
            photoItem?.let {
                Glide
                    .with(photoImage.context)
                    .load(Uri.parse(photoItem.uri))
                    .into(photoImage)
            }
        }
    }

    fun setData(data: List<PhotoItem>){
        this.data = data
        notifyDataSetChanged()
    }
}

class PhotoViewHolder(val binding: PhotoViewItemBinding) : RecyclerView.ViewHolder(binding.root)