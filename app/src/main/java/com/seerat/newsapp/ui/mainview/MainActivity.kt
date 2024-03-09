package com.seerat.newsapp.ui.mainview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.seerat.newsapp.data.models.Article
import com.seerat.newsapp.ui.CategoryAdapter
import com.seerat.newsapp.data.models.News
import com.seerat.newsapp.NewsService
import com.seerat.newsapp.databinding.ActivityMainBinding
import com.seerat.newsapp.ui.DescriptionActivity
import com.seerat.newsapp.ui.SearchActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNews()


        binding.bottomBar.iconSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

    }


    private fun getNews(){
        val news = NewsService.newsInstance.getHeadlines("in",1)
        news.enqueue(object:Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null){
                    Log.d("",news.toString())
                    newsAdapter = NewsAdapter(this@MainActivity, news.articles)

                    binding.rvItemView.adapter = newsAdapter
                    binding.rvItemView.layoutManager = LinearLayoutManager(this@MainActivity)

                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("","Error in fetching",t)
            }
        })
    }



    fun onItemClick(article: Article) {
        val intent = Intent(this, DescriptionActivity::class.java).apply {
            putExtra("title", article.title)
            putExtra("author", article.author)
            putExtra("category", article.category)
            putExtra("time", article.publishedAt)
            putExtra("content", article.content)
            putExtra("imageUrl", article.urlToImage)
        }
        startActivity(intent)
    }

}