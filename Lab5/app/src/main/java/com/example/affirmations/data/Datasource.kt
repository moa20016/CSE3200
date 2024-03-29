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
package com.example.affirmations.data

import com.example.affirmations.R
import com.example.affirmations.model.RadioStation

//import com.example.affirmations.R
//import com.example.affirmations.model.Affirmation

/**
 * [Datasource] generates a list of [RadioStation]
 */
class Datasource() {

    fun loadAffirmations(): List<RadioStation> {
        return listOf<RadioStation>(
            RadioStation(0, R.string.radiostation1_name, R.string.radiostation1_link, R.drawable.lofi),
            RadioStation(0, R.string.radiostation2_name, R.string.radiostation2_link, R.drawable.bollywood),
            RadioStation(0, R.string.radiostation3_name, R.string.radiostation3_link, R.drawable.anime),
            RadioStation(0, R.string.radiostation4_name, R.string.radiostation4_link, R.drawable.phonk),
            RadioStation(0, R.string.radiostation5_name, R.string.radiostation5_link, R.drawable.hiphop))
    }
}
            /*RadioStation(1,R.string.affirmation1, R.drawable.image1),
            RadioStation(2,R.string.affirmation2, R.drawable.image2),
            RadioStation(3,R.string.affirmation3, R.drawable.image3),
            RadioStation(4,R.string.affirmation4, R.drawable.image4),
            RadioStation(5,R.string.affirmation5, R.drawable.image5),
            RadioStation(6,R.string.affirmation6, R.drawable.image6),
            RadioStation(7,R.string.affirmation7, R.drawable.image7),
            RadioStation(8,R.string.affirmation8, R.drawable.image8),
            RadioStation(9,R.string.affirmation9, R.drawable.image9),
            RadioStation(10,R.string.affirmation10, R.drawable.image10))
    }
}
/*
             */