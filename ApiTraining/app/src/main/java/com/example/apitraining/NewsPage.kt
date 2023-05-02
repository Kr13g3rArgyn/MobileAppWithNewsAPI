package com.example.apitraining

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class NewsPage: AppCompatActivity() {

    private lateinit var backBtn: ImageView
    private lateinit var imageNews: ImageView
    private lateinit var newsTitle: TextView
    private lateinit var publishedDate: TextView
    private lateinit var text: TextView
    private lateinit var goWeb: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_page)

        backBtn = findViewById(R.id.backBtn)
        imageNews = findViewById(R.id.image_news)
        newsTitle = findViewById(R.id.news_title)
        publishedDate = findViewById(R.id.publish_date)
        text = findViewById(R.id.text)
        goWeb = findViewById(R.id.go_web)

        val arguments = intent.extras
        if (arguments != null) {
            Picasso.get()
                .load(arguments.getString("image"))
                .into(imageNews)

            newsTitle.text = arguments.getString("title")
            println(arguments.getString("publish_date"))
            publishedDate.text = arguments.getString("date")
            text.text = arguments.getString("text")
        }

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        goWeb.setOnClickListener {
            goToURL(arguments?.getString("url"))
        }


    }
    fun goToURL(url: String?) {
        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}