package com.example.timeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MyCityTimeActivity : AppCompatActivity(){

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_our_time)

        val extras = intent.extras
        if (extras != null) {
            val cityName = extras.getString("cityName")
            val countryCode = extras.getString("countryCode")
            val editTimeVal = extras.getString("editTimeVal")

            findViewById<TextView>(R.id.tvOurCity).setText(cityName)
            findViewById<TextView>(R.id.tvOurCountry).setText(countryCode)
            findViewById<TextView>(R.id.tvOurTime).setText(editTimeVal)
            //The key argument here must match that used in the other activity
        }

        findViewById<BottomNavigationView>(R.id.btmNavOur).setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.miMainPage -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.miMyTime ->{}
                R.id.miEditPage ->{
                    val intent = Intent(this, EditMyTimeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }

    }
}