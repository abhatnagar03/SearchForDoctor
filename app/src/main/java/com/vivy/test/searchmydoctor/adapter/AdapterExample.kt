package com.vivy.test.searchmydoctor.adapter

import android.content.Context
import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vivy.test.searchmydoctor.R
import com.vivy.test.searchmydoctor.R.string.base_url
import com.vivy.test.searchmydoctor.R.string.photo_end_point
import com.vivy.test.searchmydoctor.Utils.Headers
import com.vivy.test.searchmydoctor.model.Doctor
import kotlinx.android.synthetic.main.item_type_main.view.*

/**
 * Adapter class that binds data to the recycler view
 */
class AdapterExample(val context: Context, private val itemList: ArrayList<Doctor>, val itemLayout: Int)
    : RecyclerView.Adapter<AdapterExample.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(context).inflate(itemLayout, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size;
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val photoUrl = context.resources.getString(R.string.base_url) +
                context.resources.getString(R.string.photo_end_point) +
                itemList[position].photo

        holder.title.text = itemList[position].name
        holder.desc.text = itemList[position].address
        if (null != itemList[position].photo) {
            holder.image.visibility = View.VISIBLE
            Glide
                    .with(context)
                    .load(Headers.getUrlWithHeaders(photoUrl))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                    .centerCrop()
                    .crossFade()
                    .error(R.drawable.placeholder)
                    .into(holder.image)
        } else {
            holder.image.visibility = View.GONE
        }
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val title = view.txt_title!!
        val desc = view.txt_desc!!
        val image = view.imageView!!
    }
}