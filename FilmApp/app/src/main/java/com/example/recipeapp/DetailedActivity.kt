package com.example.recipeapp

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

private lateinit var song: MediaPlayer

class DetailedActivity : AppCompatActivity() {
    private lateinit var editDetailedBtn : Button
    private lateinit var textDetailed : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        // set back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val film = intent.getParcelableExtra<Film>("film")
        val textView: TextView = findViewById(R.id.detailedActivityTv)
        val imageView: ImageView =findViewById(R.id.detailedActivityIv)
        if(film!=null){


            textView.text = film.name
            imageView.setImageResource(film.image)
        }

        val playMusic= findViewById<Button>(R.id.play)
        val stopMusic = findViewById<Button>(R.id.stop)
        song = MediaPlayer.create(this, R.raw.rema)
        playMusic.setOnClickListener{
            song.start()
        }

        stopMusic.setOnClickListener{
            song.stop()
        }

        editDetailedBtn = findViewById(R.id.editDetailded)
        textDetailed = findViewById(R.id.resultText)

        editDetailedBtn.setOnClickListener{
            val updatedText = textDetailed.text.toString()
            val intent = Intent()
            intent.putExtra("updatedText", updatedText)
            setResult(Activity.RESULT_OK, intent)
            finish()

            val fragment = Home()
            val x = textDetailed.text.toString()
            val bundle = Bundle()
            bundle.putString("name", x)
            fragment.arguments=bundle
            supportFragmentManager.beginTransaction().add(R.id.frame_layout, fragment).commit()


        }


//        editButton.setOnClickListener{
//            callActivity()
//        }
    }

    override fun onStop() {
        super.onStop()

        // Stop playing the audio file when the activity is stopped
        song.stop()
    }

//    private fun callActivity(){
//        val descriptionEt = findViewById<EditText>(R.id.descriptionEt)
//        val message = descriptionEt.text.toString()
//
//        val intent = Intent(this, Home::class.java).also {
//            it.putExtra("EXTRA_MESSAGE", message)
//            startActivity(it)
//        }
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // close the current activity and go back to the previous activity
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    }