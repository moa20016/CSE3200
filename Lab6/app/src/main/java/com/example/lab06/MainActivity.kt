package com.example.lab06

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lab06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener(clickListener)
        binding.btn2.setOnClickListener(clickListener)
        binding.btn3.setOnClickListener(clickListener)
        binding.btn4.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener { view ->
        val audioUrl = when (view.id) {
            R.id.btn_1 -> "https://github.com/rafaelreis-hotmart/Audio-Sample-files/raw/master/sample.mp3"
            R.id.btn_2 -> "https://audio-edge-qse4n.yyz.g.radiomast.io/ref-128k-mp3-stereo"
            R.id.btn_3 -> "https://github.com/rafaelreis-hotmart/Audio-Sample-files/raw/master/sample.mp3"
            R.id.btn_4 -> "https://audio-edge-qse4n.yyz.g.radiomast.io/ref-128k-mp3-stereo"
            else -> ""
        }
        val videoUrl = when (view.id) {
            R.id.btn_1 -> "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            R.id.btn_2 -> "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
            R.id.btn_3 -> "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            R.id.btn_4 -> "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
            else -> ""
        }
        startMediaPlayer(audioUrl, videoUrl)
    }


    private fun startMediaPlayer(audioUrl: String, videoUrl: String) {
        val intent = Intent(this, MediaPlayer::class.java)
        intent.putExtra("audioUrl", audioUrl)
        intent.putExtra("videoUrl", videoUrl)
        startActivity(intent)
    }



}