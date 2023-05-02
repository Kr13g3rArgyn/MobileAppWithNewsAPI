package com.example.apitraining


import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.apitraining.util.NetworkUtils.Companion.generateURL
import com.example.apitraining.util.NetworkUtils.Companion.getResponseFromURL
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URL


private lateinit var bottomNavBar: BottomNavigationView
private lateinit var animeFragment: NewsFragment
private lateinit var infoFragment: InfoFragment
private lateinit var favoriteFragment: FavoriteFragment
private var currentJsonArray: JSONArray? = null



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavBar = findViewById(R.id.bottom_nav)


        animeFragment = NewsFragment()
        infoFragment = InfoFragment()
        favoriteFragment = FavoriteFragment()


        supportFragmentManager.beginTransaction().replace(R.id.main_fragment, animeFragment).commit()


        bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.anime ->{ supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, animeFragment).commit()
                    bottomNavBar.menu.getItem(2).isChecked = false
                    bottomNavBar.menu.getItem(1).isChecked = false
                    bottomNavBar.menu.getItem(0).isChecked = true}
                R.id.favorite ->{ supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, favoriteFragment).commit()
                    bottomNavBar.menu.getItem(2).isChecked = false
                    bottomNavBar.menu.getItem(0).isChecked = false
                    bottomNavBar.menu.getItem(1).isChecked = true}
                R.id.info ->{ supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, infoFragment).commit()
                    bottomNavBar.menu.getItem(0).isChecked = false
                    bottomNavBar.menu.getItem(1).isChecked = false
                    bottomNavBar.menu.getItem(2).isChecked = true}
            }

            false
        }
    }

    fun onModeChanged(){
        bottomNavBar = findViewById(R.id.bottom_nav)

        bottomNavBar.menu.getItem(2).isChecked = false
        bottomNavBar.menu.getItem(1).isChecked = false
        bottomNavBar.menu.getItem(0).isChecked = true
    }

}