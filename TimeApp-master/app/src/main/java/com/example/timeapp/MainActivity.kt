package com.example.timeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {


   private lateinit var cityTimeAdapter: CityTimeAdapter

    private lateinit var worldTimeClassTimeList: ArrayList<WorldTimeClass>

    private lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvCountries)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)


        worldTimeClassTimeList = ArrayList()
            worldTimeClassTimeList.add(WorldTimeClass("Astana","KZ","23:05"))
            worldTimeClassTimeList.add(WorldTimeClass("Moscow","RU","19:05"))
            worldTimeClassTimeList.add(WorldTimeClass("Bishkek","KG","23:05"))
            worldTimeClassTimeList.add(WorldTimeClass("Tashkent","UZ","22:05"))
            worldTimeClassTimeList.add(WorldTimeClass("Washington","US","13:05"))
            worldTimeClassTimeList.add(WorldTimeClass("Sydney","AU","03:05"))
            worldTimeClassTimeList.add(WorldTimeClass("Nuuk","GR","15:05"))
            worldTimeClassTimeList.add(WorldTimeClass("Mexico","MX","11:05"))

        cityTimeAdapter = CityTimeAdapter(worldTimeClassTimeList)
        recyclerView.adapter = cityTimeAdapter


        cityTimeAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("count", it)
            startActivity(intent)
        }

        findViewById<BottomNavigationView>(R.id.btmNavView).setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.miMainPage -> {}
                R.id.miMyTime ->{
                    val intent = Intent(this, MyCityTimeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
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