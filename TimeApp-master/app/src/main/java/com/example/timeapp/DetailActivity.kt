package com.example.timeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        var worldTimeClassD = intent.getParcelableExtra<WorldTimeClass>("count")
        if (worldTimeClassD != null) {
            var cityDetail: TextView = findViewById(R.id.tvDetailCity)
            var countryDetail: TextView = findViewById(R.id.tvDetailTime)
            var timeDetail: TextView = findViewById(R.id.tvDetailCountry)

            cityDetail.text = worldTimeClassD.nameCity
            countryDetail.text = worldTimeClassD.nameCountry
            timeDetail.text = worldTimeClassD.countryTime
        }

    }
}