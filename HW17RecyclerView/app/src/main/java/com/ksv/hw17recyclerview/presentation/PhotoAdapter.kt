package com.ksv.hw17recyclerview.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.ksv.hw17recyclerview.databinding.PhotoItemBinding
import com.ksv.hw17recyclerview.entity.DataPhotos
import com.ksv.hw17recyclerview.entity.PhotoItem
import com.ksv.hw17recyclerview.entity.PhotosItem
import com.ksv.hw17recyclerview.util.LinkCorrector

class PhotoAdapter(
    private val onClick: (PhotoItem) -> Unit
): RecyclerView.Adapter<PhotoViewHolder>() {
    private var data : List<PhotoItem> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoItem = data.getOrNull(position)
        with(holder.binding){
            tvCamera.text = "Camera: ${photoItem?.camera?.name}"
            tvDate.text = "Date: ${photoItem?.date}"
            tvSol.text = "Sol: ${photoItem?.sol?.toString()}"
            tvRover.text = "Rover: ${photoItem?.rover?.name}"
            photoItem?.let {
                Glide
                    .with(image.context)
                    .load(LinkCorrector.change(it.url))
                    .into(image)
            }
        }
        holder.binding.root.setOnClickListener {
            photoItem?.let(onClick)
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<PhotoItem>){
        this.data = data
        notifyDataSetChanged()
    }

}

class PhotoViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)