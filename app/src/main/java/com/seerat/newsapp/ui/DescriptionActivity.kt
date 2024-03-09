package com.seerat.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.seerat.newsapp.databinding.ActivityDescriptionBinding

class DescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptionBinding
    private lateinit var title: String
    private lateinit var author: String
    private lateinit var category: String
    private lateinit var time: String
    private lateinit var content: String
    private lateinit var description: String
    private lateinit var imageUrl: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = intent.getStringExtra("title").toString()
        author = intent.getStringExtra("author").toString()
        category = intent.getStringExtra("category").toString()
        time = intent.getStringExtra("time").toString()
        //content = intent.getStringExtra("content").toString()
        description = intent.getStringExtra("description").toString()
        imageUrl = intent.getStringExtra("imageUrl").toString()

        binding.descriptionTitle.text = title
        binding.descriptionAuthor.text = author
        binding.txtCategory.text = category
        binding.time.text = time
        binding.descriptionContent.text = description

        Glide.with(this)
            .load(imageUrl)
            .into(binding.descriptionViewImge)
    }

}
