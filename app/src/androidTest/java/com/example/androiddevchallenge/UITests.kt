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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.androiddevchallenge.ui.theme.MyTheme
import org.junit.Rule
import org.junit.Test
import java.util.*

class UITests {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Test
    fun openBottomSheetAndCloseBottomSheetTest() {
        Locale.setDefault(Locale("en", "EN"))

        composeTestRule.setContent {
            MyTheme() {
                MyApp()
            }
        }
        composeTestRule.onNodeWithTag(testTag= "OpenBottomSheetFloatingButton").assertExists()
        composeTestRule.onNodeWithTag(testTag = "CloseBottomSheetFloatingButton")
            .assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag= "OpenBottomSheetFloatingButton").performClick()
        composeTestRule.onNodeWithTag(testTag= "OpenBottomSheetFloatingButton")
            .assertDoesNotExist()
        composeTestRule.onNodeWithTag(testTag = "CloseBottomSheetFloatingButton").assertExists()
        composeTestRule.onNodeWithTag(testTag = "CloseBottomSheetFloatingButton").performClick()
        composeTestRule.onNodeWithTag(testTag= "OpenBottomSheetFloatingButton").assertExists()
        composeTestRule.onNodeWithTag(testTag= "CloseBottomSheetFloatingButton")
            .assertDoesNotExist()
    }

    @ExperimentalAnimationApi
    @Test
    fun changeTemperatureTypeTest() {
        Locale.setDefault(Locale("en", "EN"))

        composeTestRule.setContent {
            MyTheme() {
                MyApp()
            }
        }
        composeTestRule.onNodeWithText("DAY 42°").assertExists()
        composeTestRule.onNodeWithText("DAY 107°").assertDoesNotExist()
        composeTestRule.onNodeWithTag("changeTemperatureButton").performClick()
        composeTestRule.onNodeWithText("DAY 42°").assertDoesNotExist()
        composeTestRule.onNodeWithText("DAY 107°").assertExists()
        composeTestRule.onNodeWithTag("changeTemperatureButton").performClick()
        composeTestRule.onNodeWithText("DAY 42°").assertExists()
        composeTestRule.onNodeWithText("DAY 107°").assertDoesNotExist()
    }
    @ExperimentalAnimationApi
    @Test
    fun clickingOnNext7DaysMakesThe7DaysTabAppearTest(){
        Locale.setDefault(Locale("en", "EN"))

        composeTestRule.setContent {
            MyTheme {
                MyApp()
            }
        }
//        initially these days should not be there
        composeTestRule.onNodeWithTag("next7Days").assertDoesNotExist()

        composeTestRule.onNodeWithContentDescription(label = "Open Bottom Sheet").performClick()
        composeTestRule.onNodeWithTag("Next7DaysTextButton").performClick()
        composeTestRule.onNodeWithTag("next7Days").assertExists()

    }
    }
