package com.example.mgarderobe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter (private val articles: MutableList<Article>):
    RecyclerView.Adapter<CustomAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itmeView: View): RecyclerView.ViewHolder(itmeView){

        val nameTV: TextView = itmeView.findViewById(R.id.itemNameTextViewTV)
        val descriptionTV: TextView = itmeView.findViewById(R.id.itemDescriptionTextViewTV)
        val imageTV: ImageView = itmeView.findViewById(R.id.itemImageViewIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return ArticleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.nameTV.text = article.name
        holder.descriptionTV.text = article.description
        holder.imageTV.setImageResource(article.image)
    }
}