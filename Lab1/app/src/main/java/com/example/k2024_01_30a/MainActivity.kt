package com.example.k2024_01_30a

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {

    private val setVar = "PGB_LOG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Text(text = "Hello, World!")
        }

        Log.d(setVar, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(setVar, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(setVar, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(setVar, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(setVar, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(setVar, "onDestroy")
    }
}