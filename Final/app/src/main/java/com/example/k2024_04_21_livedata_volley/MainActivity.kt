package com.example.k2024_04_21_livedata_volley

import android.graphics.Bitmap
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.k2024_04_21_livedata_volley.databinding.ActivityMainBinding
import com.example.k2024_04_21_livedata_volley.models.JSON_MetMuseum
import com.example.k2024_04_21_livedata_volley.view_models.UrlViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val gson = Gson()
    private val metPublicDomainUrl = "https://collectionapi.metmuseum.org/public/collection/v1/objects/"
    private var imageData : JSON_MetMuseum? = null
    private lateinit var volleyQueue: RequestQueue
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        volleyQueue = Volley.newRequestQueue(this)

        val uriViewModel: UrlViewModel by viewModels()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRadio()

        binding.nextImageButton.setOnClickListener {
            val nextIndex = uriViewModel.nextImageNumber()
            val metUrl = metPublicDomainUrl + nextIndex.toString()
            uriViewModel.setMetaDataUrl(metUrl)
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                uriViewModel.getMetaDataUrl(),
                null,
                { response ->
                    imageData = gson.fromJson(response.toString(), JSON_MetMuseum::class.java )
                    val title = "Title: ${imageData?.title}\n"
                    val dates = "Dates: ${imageData?.objectBeginDate}-${imageData?.objectEndDate}\n"
                    val country = "Country: ${imageData?.country}\n"
                    val objectName = "Object Name: ${imageData?.objectName}\n"
                    val text = title + dates + country + objectName
                    binding.textView.text = text

                    val imageUrl = imageData?.primaryImage.toString()
                    if (imageUrl.isNotEmpty()) {
                        uriViewModel.setImageUrl(imageUrl)
                        val imageRequest = ImageRequest(
                            imageUrl,
                            { imageResponse: Bitmap ->
                                binding.imageView.setImageBitmap(imageResponse)
                            },
                            0,0,
                            ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,
                            { error ->  Log.i("PGB", "Error: ${error}" )})

                        volleyQueue.add(imageRequest)
                    } else {
                        binding.imageView.setImageResource(R.drawable.default_image)
                    }
                },
                { error ->  Log.i("PGB" ,"Error: ${error}") })
            volleyQueue.add(jsonObjectRequest)
        }
    }

    private fun setUpRadio() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource("https://stream.epic-classical.com/classical-piano")
            prepareAsync()
            setOnPreparedListener { mp -> mp.start() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
