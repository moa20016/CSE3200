package com.example.k2022_03_09_radio

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

var Index = 0

val urls = arrayOf(
    "https://lofi.stream.laut.fm/lofi",
    "https://vm65162.streamerr.co/listen/megazone_bollywood_/radio.mp3",
    "https://azura12.instainternet.com/radio/8030/radio.mp3",
    "https://phonk.stream.laut.fm/phonk",
    "https://kawaii-music.stream.laut.fm/kawaii-music"
)

val videoUrls = arrayOf(
    "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
    "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
    "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
    "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"
)


class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    private lateinit var radioButton: Button
    private lateinit var leftVidButton: Button
    private lateinit var rightVidButton: Button
    private lateinit var videoButton: Button
    private lateinit var imageView: ImageView
    private lateinit var stationRecyclerView: RecyclerView
    private lateinit var stationAdapter: StationAdapter

    private lateinit var mediaPlayer: MediaPlayer
    private var radioOn: Boolean = false

    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer

    private var isVideoPlaying: Boolean = false
    private var currentVideoIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)
        radioButton = findViewById(R.id.radioButton)
        leftVidButton = findViewById(R.id.leftVidButton)
        rightVidButton = findViewById(R.id.rightVidButton)
        videoButton = findViewById(R.id.videoButton)
        imageView = findViewById(R.id.imageView)
        stationRecyclerView = findViewById(R.id.stationRecyclerView)
        playerView = findViewById(R.id.player_view)

        setUpRadio()
        setUpVideoPlayer()

        button.setOnClickListener {
                toggleRadio()
        }

        leftButton.setOnClickListener {
                flipStation(-1)
        }

        rightButton.setOnClickListener {
            flipStation(1)
        }

        leftVidButton.setOnClickListener {
            previousVideo()
        }

        rightVidButton.setOnClickListener {
            nextVideo()
        }

        videoButton.setOnClickListener {
            toggleVideo()
        }

        stationRecyclerView.layoutManager = LinearLayoutManager(this)

        stationAdapter = StationAdapter(urls) { position ->
            flipStation(position - Index)
            toggleRadio()
        }
        stationRecyclerView.adapter = stationAdapter
    }

    private fun setUpRadio() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(urls[Index])
            prepareAsync()
        }

        mediaPlayer.setOnPreparedListener {
            radioOn = false
            updateRadioButtonText()
        }
    }

    private fun setUpVideoPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView.player = player
        val mediaItem = MediaItem.fromUri(videoUrls[currentVideoIndex])
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    private fun toggleRadio() {
        radioOn = !radioOn
        updateRadioButtonText()

        if (radioOn) {
            mediaPlayer.start()
        } else {
            mediaPlayer.pause()
        }
    }

    private fun toggleVideo() {
        if (isVideoPlaying) {
            player.pause()
        } else {
            player.play()
        }
        isVideoPlaying = !isVideoPlaying
    }

    private fun updateRadioButtonText() {
            button.text = if (radioOn) "Radio Off" else "Radio On"
    }

    private fun flipStation(offset: Int) {
        val newIndex = (Index + offset + urls.size) % urls.size
        mediaPlayer.reset()
        mediaPlayer.setDataSource(urls[newIndex])
        mediaPlayer.prepareAsync()
        radioButton.text = urls[newIndex]

        val imageId = getImageResourceId(urls[newIndex])
        imageView.setImageResource(imageId)

        if (radioOn) {
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
            }
        }

        Index = newIndex
    }

    private fun getImageResourceId(url: String): Int {
        // Map radio station URLs to image resource IDs
        return when (url) {
            "https://lofi.stream.laut.fm/lofi" -> R.drawable.lofi
            "https://vm65162.streamerr.co/listen/megazone_bollywood_/radio.mp3" -> R.drawable.bollywood
            "https://azura12.instainternet.com/radio/8030/radio.mp3" -> R.drawable.anime
            "https://phonk.stream.laut.fm/phonk" -> R.drawable.phonk
            "https://kawaii-music.stream.laut.fm/kawaii-music" -> R.drawable.kawaii
            else -> R.drawable.cover2
        }
    }


    private fun nextVideo() {
        currentVideoIndex = (currentVideoIndex + 1) % videoUrls.size
        val mediaItem = MediaItem.fromUri(videoUrls[currentVideoIndex])
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    private fun previousVideo() {
        currentVideoIndex = (currentVideoIndex - 1 + videoUrls.size) % videoUrls.size
        val mediaItem = MediaItem.fromUri(videoUrls[currentVideoIndex])
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.release()
    }
}
