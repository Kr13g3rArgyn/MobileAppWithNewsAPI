package com.example.timeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class EditMyTimeActivity : AppCompatActivity(){

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)


        findViewById<Button>(R.id.editButton).setOnClickListener {
//            val temp = findViewById<EditText>(R.id.etCityName).text.toString()
//            Log.d("EditMyTimeActivity", temp)

            val intent = Intent(this, MyCityTimeActivity::class.java)

            val cityName = findViewById<EditText>(R.id.etCityName).text.toString()
            val countryCode = findViewById<EditText>(R.id.etCountryCode).text.toString()
            val editTimeVal  = findViewById<EditText>(R.id.etEditTimeVal).text.toString()

            intent.putExtra("cityName", cityName)
            intent.putExtra("countryCode", countryCode)
            intent.putExtra("editTimeVal", editTimeVal)
            startActivity(intent)
        }
        findViewById<BottomNavigationView>(R.id.btmNavOur).setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.miMainPage -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.miMyTime ->{
                    val intent = Intent(this, MyCityTimeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.miEditPage ->{

                }
            }
            true
        }

    }
}