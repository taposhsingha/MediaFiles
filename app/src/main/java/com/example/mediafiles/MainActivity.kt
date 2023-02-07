package com.example.mediafiles

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer.create(this,R.raw.applauding)

        val play = findViewById<Button>(R.id.btnPlay)
        val pause = findViewById<Button>(R.id.btnPause)
        val stop = findViewById<Button>(R.id.btnStop)
        play.setOnClickListener {
            mediaPlayer.start()
        }
        pause.setOnClickListener {
            mediaPlayer.pause()
        }
        stop.setOnClickListener {

            mediaPlayer.reset()
        }
    }
}