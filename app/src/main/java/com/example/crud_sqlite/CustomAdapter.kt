package com.example.crud_sqlite

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_sqlite.databinding.ItemDataBinding

class CustomAdapter(val activity: Activity, val context: Context): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    private var book_id = ArrayList<String>()
    private var book_title = ArrayList<String>()
    private var book_author = ArrayList<String>()
    private var book_pages = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.tvID.text = book_id[position]
            binding.tvTitle.text = book_title[position]
            binding.tvAuthor.text = book_author[position]
            binding.tvPages.text = book_pages[position]
            binding.cvItem.setOnClickListener {
                val intent = Intent(context, UpdateActivity::class.java)
                intent.putExtra("id", book_id[position])
                intent.putExtra("title", book_title[position])
                intent.putExtra("author", book_author[position])
                intent.putExtra("pages", book_pages[position])
                activity.startActivityForResult(intent, 1)
            }

        }
    }

    override fun getItemCount(): Int {
        return book_id.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemDataBinding.bind(itemView)
    }

    fun setData(
        nBook_id: ArrayList<String>,
        nBook_title: ArrayList<String>,
        nBook_author: ArrayList<String>,
        nBook_pages: ArrayList<String>,
    ){
        book_id = nBook_id
        book_title = nBook_title
        book_author = nBook_author
        book_pages = nBook_pages
    }
}