package com.seerat.newsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.seerat.newsapp.NewsService
import com.seerat.newsapp.data.models.News
import com.seerat.newsapp.databinding.ActivitySearchBinding
import com.seerat.newsapp.ui.mainview.MainActivity
import com.seerat.newsapp.ui.mainview.NewsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity(),TextWatcher {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var newsAdapter: NewsAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomBar.iconHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.searchBar.searchbarEt.addTextChangedListener(this)
    }


    private fun searchNews(searchQuery: String){
        val news = NewsService.newsInstance.searchNews(searchQuery, "2024-03-07")
        news.enqueue(object: Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null){
                    Log.d("",news.toString())
                    newsAdapter = NewsAdapter(this@SearchActivity, news.articles)

                    binding.rvSearch.adapter = newsAdapter
                    binding.rvSearch.layoutManager = LinearLayoutManager(this@SearchActivity)

                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("","Error in fetching",t)
            }
        })
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString().isNotBlank()){
            searchNews(s.toString())
        }
    }


}