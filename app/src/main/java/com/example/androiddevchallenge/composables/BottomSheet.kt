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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.*
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.*

@ExperimentalAnimationApi
@Composable
fun BottomSheet(
    expanded: Boolean,
    temperatureType: TemperatureType,
    theme: TimeBasedTheme,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sunriseTime = remember { mutableStateOf(generateRandomTime(5, 7)) }
    val windTime = remember { mutableStateOf(generateRandomTime(8,10)) }
    val sunsetTime = remember { mutableStateOf(generateRandomTime(18,20)) }
    val expandedSheetState  = remember {
        mutableStateOf(ExpandedSheetState.Today)
    }
    var height = 350
    if (expanded)
        height = 742
    var floatingButtonContentDescription = "Open Bottom Sheet"
    var testTag = "OpenBottomSheetFloatingButton"
    if (expanded) {
        floatingButtonContentDescription = "Close Bottom Sheet"
        testTag = "CloseBottomSheetFloatingButton"
    }
    val convertToFahrenheit = temperatureType == TemperatureType.Fahrenheit

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {

        Box(
            modifier = Modifier
                .height(height.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 40.dp, topEnd = 40.dp
                    )
                )
                .background(color = if (expanded) borderColor else Color.Transparent)
        ) {
            if (expanded)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        stringResource(id = if(expandedSheetState.value == ExpandedSheetState.Tomorrow) R.string.today else R.string.tomorrow), style = myStyleBlackColor.copy(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,

                        ),
                        modifier = Modifier.clickable {
                            if(expandedSheetState.value!= ExpandedSheetState.Tomorrow)
                            expandedSheetState.value = ExpandedSheetState.Tomorrow
                            else
                                expandedSheetState.value = ExpandedSheetState.Today
                        }
                    )
                    Text(
                        stringResource(id = if(expandedSheetState.value == ExpandedSheetState.Next7Days) R.string.today else R.string.next_7_days), style = myStyleBlackColor.copy(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium
                        ),modifier = Modifier.clickable {
                            if(expandedSheetState.value!= ExpandedSheetState.Next7Days)
                                expandedSheetState.value = ExpandedSheetState.Next7Days
                            else
                                expandedSheetState.value = ExpandedSheetState.Today
                        }.testTag(tag = "Next7DaysTextButton")
                    )
                }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .animateContentSize(), verticalArrangement = Arrangement.Bottom
            ) {
                if (expanded)
                    ExpandedContent(convertToFahrenheit, sunriseTime.value , sunsetTime.value , windTime.value ,expandedSheetState.value)
                else
                    UnExpandedContent(sunriseTime.value , sunsetTime.value , windTime.value )
            }

            androidx.compose.animation.AnimatedVisibility(visible = !expanded) {
                BottomSheetCenter(
                    theme = theme,
                    temperatureType = temperatureType,
                    modifier = Modifier.padding(top = 50.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 36.dp, top = if (expanded) 50.dp else 75.dp),
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(
                    onClick = onClick,
                    backgroundColor = theme.fabColor,
                    modifier = Modifier
                        .size(36.dp)
                        .semantics() {
                            contentDescription = floatingButtonContentDescription
                        }.testTag(tag =testTag)
                ) {
                    Icon(
                        if (expanded) Icons.Filled.ArrowDownward else Icons.Filled.ArrowUpward,
                        tint = theme.fabIconColor,
                        contentDescription = null
                    )
                }
            }

        }
    }
}

@ExperimentalAnimationApi
@Preview(widthDp = 600, heightDp = 250)
@Composable
fun BottomSheetPreview() {
    Surface(color = Color.White) {
        BottomSheet(
            expanded = true,
            temperatureType = TemperatureType.Celsius,
            theme = getTheme(),
            onClick = {

            })
    }
}
