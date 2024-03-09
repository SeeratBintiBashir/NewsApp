package com.seerat.newsapp.ui.mainview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seerat.newsapp.data.models.Article
import com.seerat.newsapp.R
import com.seerat.newsapp.ui.DescriptionActivity

class NewsAdapter(private val context: Context, private val articles: List<Article>)
    : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImage: ImageView = itemView.findViewById(R.id.item_view_image)
        var newsTitle: TextView = itemView.findViewById(R.id.news_title)
        var newsAuthor: TextView = itemView.findViewById(R.id.author)
        var newsCategory: TextView = itemView.findViewById(R.id.news_category)
        var newsTime: TextView = itemView.findViewById(R.id.time)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return ArticleViewHolder(view)

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.newsTitle.text = article.title
        holder.newsAuthor.text = article.author
        holder.newsCategory.text = article.category
        holder.newsTime.text = article.publishedAt



        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DescriptionActivity::class.java).apply {
                putExtra("title", article.title)
                putExtra("author", article.author)
                putExtra("category", article.category)
                putExtra("time", article.publishedAt)
                putExtra("content", article.content)
                putExtra("description", article.description)
                putExtra("imageUrl", article.urlToImage)

            }
            holder.itemView.context.startActivity(intent)
        }


    }


}


