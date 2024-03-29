/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.RadioStation
import com.example.affirmations.ui.theme.AffirmationsTheme
import com.example.affirmations.view_model.MediaPlayerViewModel

class MainActivity : ComponentActivity() {

    private val mediaPlayerViewModel by viewModels<MediaPlayerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp(context = applicationContext, mediaPlayerViewModel = mediaPlayerViewModel)
                }
            }
        }
    }
}


@Composable
fun AffirmationCard(context: Context, radioStation: RadioStation, mediaPlayerViewModel: MediaPlayerViewModel, modifier: Modifier = Modifier) {
    val isPlaying = remember { mutableStateOf(false) }

    Card(
        modifier = modifier.clickable {
            if (isPlaying.value) {
                mediaPlayerViewModel.pauseAudio()
                isPlaying.value = false
            } else {
                mediaPlayerViewModel.playAudio(context, context.getString(radioStation.url))
                isPlaying.value = true
            }
        }
    ){
        Column() {
            Image(
                painter = painterResource(id = radioStation.imageResourceID),
                contentDescription = stringResource(id = radioStation.radioStationName),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = context.getString(radioStation.radioStationName),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}



@Composable
fun AffirmationsApp(context: Context, mediaPlayerViewModel: MediaPlayerViewModel){
    val radioStationList = Datasource().loadAffirmations()
    AffirmationList(radioStationList = radioStationList, context = context, mediaPlayerViewModel = mediaPlayerViewModel)
}

@Composable
fun AffirmationList(radioStationList: List<RadioStation>, context: Context, mediaPlayerViewModel: MediaPlayerViewModel, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier){
        items(radioStationList) { affirmation ->
            AffirmationCard(
                context = context,
                radioStation = affirmation,
                mediaPlayerViewModel = mediaPlayerViewModel,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}





