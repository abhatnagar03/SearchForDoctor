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
import com.vivy.test.searchmydoctor.model.Doctor
import kotlinx.android.synthetic.main.item_type_main.view.*

/**
 * Adapter class that binds data to the recycler view
 */
class AdapterExample(val context: Context, val itemList: ArrayList<Doctor>, val itemLayout: Int)
    : RecyclerView.Adapter<AdapterExample.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(context).inflate(itemLayout, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size;
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.title?.text = itemList.get(position).name
        holder.desc?.text = itemList.get(position).address
        if(null != itemList.get(position).photo) {
            holder.image.visibility = View.VISIBLE
            Glide
                    .with(context)
                    .load(itemList.get(position).photo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                    .centerCrop()
                    .crossFade()
                    .error(R.drawable.placeholder)
                    .into(holder.image)
        } else {
            holder.image.visibility = View.GONE
        }
    }

    class ItemHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val title = view.txt_title
        val desc = view.txt_desc
        val image = view.imageView
    }
}