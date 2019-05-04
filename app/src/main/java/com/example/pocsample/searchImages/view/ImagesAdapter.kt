package com.example.pocsample.searchImages.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.pocsample.R
import com.example.pocsample.searchImages.data.Image
import kotlinx.android.synthetic.main.image_list_item.view.*


class ImagesAdapter(
    private val images: List<Image>

) : RecyclerView.Adapter<ImagesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): ImagesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImagesViewHolder(inflater.inflate(R.layout.image_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(
        viewHolder: ImagesViewHolder,
        position: Int
    ) {
        viewHolder.bind(images[position])
    }
}

class ImagesViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bind(image:  Image) {
        Glide.with(itemView.context)
            .load(image.pageMap.cse_image.source) // Remote URL of image.
            .into(itemView.imageView); //ImageView to set.
    }
}
