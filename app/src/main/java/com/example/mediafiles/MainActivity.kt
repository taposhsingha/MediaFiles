package com.example.mediafiles

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    private lateinit var seekBar:SeekBar
    private var mediaPlayer: MediaPlayer? =null
    private lateinit var runnable: Runnable
    private lateinit var handler:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar = findViewById(R.id.seekBar)
        handler = Handler(Looper.getMainLooper())

        val play = findViewById<Button>(R.id.btnPlay)
        val pause = findViewById<Button>(R.id.btnPause)
        val stop = findViewById<Button>(R.id.btnStop)
        play.setOnClickListener {
            if(mediaPlayer==null){
                mediaPlayer = MediaPlayer.create(this,R.raw.applauding)
                initializeSeekBar()
            }
            mediaPlayer?.start()
        }
        pause.setOnClickListener {
            mediaPlayer?.pause()
        }
        stop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
            handler.removeCallbacks(runnable)
            seekBar.progress = 0
        }
    }
    private fun initializeSeekBar(){
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seekBar.max = mediaPlayer!!.duration
        runnable = Runnable {
            seekBar.progress = mediaPlayer!!.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)
    }
}