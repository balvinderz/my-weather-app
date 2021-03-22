/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.blackish
import com.example.androiddevchallenge.ui.theme.myStyle

@Composable
fun SunriseWindSunsetTime(
    sunriseTime: String,
    windTime: String,
    sunsetTime: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TimeColumn(
            iconId = R.drawable.sunrise,
            text = stringResource(id = R.string.sunrise),
            time = sunriseTime,
        )
        TimeColumn(
            iconId = R.drawable.wind,
            text = stringResource(id = R.string.wind),
            time = windTime,
        )
        TimeColumn(
            iconId = R.drawable.sunset,
            text = stringResource(id = R.string.sunset),
            time = sunsetTime,
        )
    }
}

@Composable
private fun TimeColumn(
    iconId: Int,
    text: String,
    time: String,
    modifier: Modifier = Modifier,
) {
    val contentDescriptionForTimeColumn = "$text at $time"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.semantics(mergeDescendants = true) {
            contentDescription = contentDescriptionForTimeColumn
        }
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null
        )
        Text(
            text,
            style = myStyle.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = blackish
            )
        )
        Text(
            time,
            style = myStyle.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = blackish
            )
        )
    }
}
