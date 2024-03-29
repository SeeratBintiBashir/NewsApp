package com.seerat.newsapp

import com.seerat.newsapp.data.models.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "dd54a95ec8734bf194424c55594a79c0"

interface NewsInterface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country: String,@Query("page")page: Int) : Call<News>
    @GET("v2/everything?sortBy=publishedAt&apiKey=$API_KEY")
    fun searchNews(@Query("q")searchQuery: String,@Query("from")from: String) : Call<News>
}

object NewsService{
    val newsInstance: NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }

}

