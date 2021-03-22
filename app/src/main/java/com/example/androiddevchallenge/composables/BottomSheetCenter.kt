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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.TemperatureType
import com.example.androiddevchallenge.convertToFahrenheit
import com.example.androiddevchallenge.getCurrentTime
import com.example.androiddevchallenge.ui.theme.TimeBasedTheme
import com.example.androiddevchallenge.ui.theme.blackish
import com.example.androiddevchallenge.ui.theme.myStyle

@Composable
fun BottomSheetCenter(theme: TimeBasedTheme, temperatureType: TemperatureType,modifier: Modifier = Modifier) {
    val temperatureTypeContentDescription = if(temperatureType == TemperatureType.Fahrenheit) stringResource(id = R.string.farhenheit) else stringResource(
        id = R.string.celsius
    )
    val convertToFahrenheit = temperatureType == TemperatureType.Fahrenheit
    val contentDescriptionForCenterElement : String = stringResource(id = R.string.read_temperature,37.convertToFahrenheit(convertToFahrenheit),temperatureTypeContentDescription,
        getCurrentTime())
    Row(
        modifier = modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {
                contentDescription =
                    contentDescriptionForCenterElement
            },
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(69.dp)
                .clip(
                    RoundedCornerShape(
                        11.dp
                    )
                )
                .background(color = theme.cardColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.weather_icons_01),
                    contentDescription = null,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.thermometer),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(
                        "${37.convertToFahrenheit(convertToFahrenheit)}°",
                        style = myStyle.copy(
                            fontWeight = FontWeight.Normal,
                            color = blackish,
                            fontSize = 19.sp
                        )
                    )
                    Text(
                        if (temperatureType == TemperatureType.Celsius)"C" else "F",
                        style = myStyle.copy(
                            fontWeight = FontWeight.Normal,
                            color = blackish,
                            fontSize = 11.sp
                        )
                    )
                }

                Column(modifier = Modifier
                    .padding(top = 5.dp)
                    .height(26.dp)
                    .fillMaxWidth()
                    .background(color = theme.fabColor), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        getCurrentTime(),
                        style = myStyle.copy(
                            color = blackish,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.5.sp
                        ),
                    )
                }
            }
        }
    }
}
