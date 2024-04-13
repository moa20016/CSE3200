package com.example.lab06

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.lab06.databinding.ActivityMediaPlayerBinding

class MediaPlayer : AppCompatActivity() {
    private lateinit var binding: ActivityMediaPlayerBinding
    private lateinit var audioPlayer: ExoPlayer
    private lateinit var videoPlayer: ExoPlayer
    private lateinit var audioUrl: String
    private lateinit var videoUrl: String
    private lateinit var audioFocusManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        audioFocusManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        audioUrl = intent.getStringExtra("audioUrl")!!
        videoUrl = intent.getStringExtra("videoUrl")!!

        audioPlayer = ExoPlayer.Builder(this).build()
        binding.audioPlayerView.player = audioPlayer

        videoPlayer = ExoPlayer.Builder(this).build()
        binding.videoPlayerView.player = videoPlayer

        val audioMediaItem = MediaItem.fromUri(audioUrl)
        val videoMediaItem = MediaItem.fromUri(videoUrl)

        audioPlayer.setMediaItem(audioMediaItem)
        videoPlayer.setMediaItem(videoMediaItem)

        audioPlayer.prepare()
        videoPlayer.prepare()
    }

    override fun onStart() {
        super.onStart()
        audioPlayer.playWhenReady = true
        videoPlayer.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        audioPlayer.playWhenReady = false
        videoPlayer.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        audioPlayer.release()
        videoPlayer.release()
    }

    private fun requestAudioFocus() {
        audioFocusManager.requestAudioFocus(
            null,
            AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN
        )
    }

    private fun abandonAudioFocus() {
        audioFocusManager.abandonAudioFocus(null)
    }
}
