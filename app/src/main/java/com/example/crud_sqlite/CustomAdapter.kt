package com.example.crud_sqlite

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_sqlite.databinding.ItemDataBinding

class CustomAdapter(val activity: Activity, val context: Context): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

   var arrayList = ArrayList<BookModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = arrayList[position]
        with(holder){
            binding.tvID.text = book.id
            binding.tvTitle.text = book.title
            binding.tvAuthor.text = book.author
            binding.tvPages.text = book.pages
            binding.cvItem.setOnClickListener {
                val intent = Intent(context, UpdateActivity::class.java)
                intent.putExtra("id", book.id)
                intent.putExtra("title", book.title)
                intent.putExtra("author", book.author)
                intent.putExtra("pages", book.pages)
                activity.startActivityForResult(intent, 1)
            }
            //Animate Recyclerview
            val translateAnim = AnimationUtils.loadAnimation(context, R.anim.translate_anim)
            binding.cvItem.animation = translateAnim

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemDataBinding.bind(itemView)
    }

    fun setData(
        newList: ArrayList<BookModel>
    ){
       arrayList = newList
    }
}