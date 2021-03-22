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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.androiddevchallenge.composables.BottomSheet
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.TimeBasedTheme
import com.example.androiddevchallenge.ui.theme.blackish
import com.example.androiddevchallenge.ui.theme.myStyle

class MainActivity : AppCompatActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp() {
    val theme: TimeBasedTheme = getTheme()

    val expanded = remember {
        mutableStateOf(false)
    }
    val temperatureType = remember {
        mutableStateOf(TemperatureType.Celsius)
    }
    val convertToFahrenheit = temperatureType.value == TemperatureType.Fahrenheit
    val temperatureTypeContentDescription =
        if (convertToFahrenheit) stringResource(id = R.string.farhenheit) else stringResource(
            id = R.string.celsius
        )
    Surface(color = MaterialTheme.colors.background) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(

                        colors = theme.gradientColors

                    )
                )
        ) {
            if (!expanded.value)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            if (temperatureType.value == TemperatureType.Fahrenheit)
                                temperatureType.value = TemperatureType.Celsius
                            else
                                temperatureType.value = TemperatureType.Fahrenheit
                        },
                        modifier = Modifier.semantics {
                            contentDescription = if (!convertToFahrenheit)"Celsius to Fahrenheit" else "Fahrenheit to Celsius"
                        }.testTag(tag = "changeTemperatureButton")
                    ) {
                        Text(
                            "C°",
                            style = myStyle.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = theme.textColor
                            ),
                            modifier = Modifier
                                .alpha(
                                    getAlpha(
                                        temperatureType = temperatureType.value,
                                        type = "Celsius"
                                    )
                                )
                        )
                        Text(
                            "/F°",
                            style = myStyle.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = theme.textColor

                            ),
                            modifier = Modifier
                                .alpha(
                                    getAlpha(
                                        temperatureType = temperatureType.value,
                                        type = "Fahrenheit"
                                    )
                                )
                        )
                    }
                }

            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Image(
                    painter = painterResource(id = theme.backgroundImage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 200.dp),
                    contentScale = ContentScale.FillWidth
                )
            }
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Box(
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topEnd = 40.dp,
                                topStart = 40.dp
                            )
                        )
                        .background(Color.White)
                )
            }
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {

                    BottomSheet(
                        expanded = expanded.value,
                        temperatureType = temperatureType.value,
                        theme = theme,
                        onClick = {
                            expanded.value = !expanded.value
                        }
                    )
                }
            }
            if (!expanded.value)
                Column() {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            "${stringResource(id = R.string.today)}, ${getTodaysDate()}",
                            color = theme.textColor,
                            style = myStyle,
                            modifier = Modifier.padding(top = 76.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "India",
                            style = myStyle.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 17.sp,
                                color = theme.textColor,

                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            val dayText =
                                stringResource(id = R.string.day) + " " + 42.convertToFahrenheit(
                                    convertToFahrenheit
                                ) + "°"
                            val nightText =
                                stringResource(id = R.string.night) + " " + 28.convertToFahrenheit(
                                    convertToFahrenheit
                                ) + "°"
                            Column() {
                                Text(
                                    dayText,
                                    style = myStyle.copy(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 17.sp,
                                        color = theme.textColor,

                                    ),
                                    modifier = Modifier.semantics {
                                        contentDescription =
                                            "$dayText$temperatureTypeContentDescription"
                                    }
                                )
                                Text(
                                    nightText,
                                    style = myStyle.copy(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 17.sp,
                                        color = theme.textColor,

                                    ),
                                    modifier = Modifier.semantics {
                                        contentDescription =
                                            "$nightText$temperatureTypeContentDescription"
                                    }
                                )
                            }
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.End
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .semantics(mergeDescendants = true) {
                                            contentDescription =
                                                "${27.convertToFahrenheit(convertToFahrenheit)}°$temperatureTypeContentDescription"
                                        }.testTag(tag = "currentTemperature"),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = getIcon(theme)),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(10.3.dp))
                                    Text(
                                        "${27.convertToFahrenheit(convertToFahrenheit)}",
                                        style = myStyle.copy(
                                            fontSize = 59.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = theme.textColor,

                                        ),
                                        modifier = Modifier.testTag(tag = "currentTemperature")
                                    )
                                    Text(
                                        "°",
                                        style = myStyle.copy(
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 29.sp,
                                            color = theme.textColor,

                                        )
                                    )
                                    Text(
                                        if (temperatureType.value == TemperatureType.Celsius) "C" else "F",
                                        style = myStyle.copy(
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 36.sp,
                                            color = theme.textColor,

                                        ),
                                    )
                                }
                                Text(
                                    stringResource(id = R.string.sunny_with_periodic) + " \n" + stringResource(
                                        id = R.string.clouds
                                    ),
                                    style = myStyle.copy(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 15.sp,
                                        color = theme.textColor,

                                    ),
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
        }
    }
}

fun getAlpha(temperatureType: TemperatureType, type: String): Float {
    if (type == "Fahrenheit" && temperatureType == TemperatureType.Fahrenheit) {
        return 1.0f
    } else {
        if (type == "Celsius" && temperatureType == TemperatureType.Celsius)
            return 1.0f
    }
    return 0.5f
}

fun getIcon(theme: TimeBasedTheme): Int {
    if (theme.textColor == blackish)
        return R.drawable.sunny_with_cloud_black
    return R.drawable.sunny_with_cloud
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
