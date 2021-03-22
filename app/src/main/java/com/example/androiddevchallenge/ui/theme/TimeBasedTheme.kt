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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.R

abstract class TimeBasedTheme {

    abstract val gradientColors: List<Color>
    abstract val fabColor: Color
    abstract val cardColor: Color
    abstract val textColor: Color
    abstract val backgroundImage: Int
    abstract val fabIconColor: Color
}
class SunriseTheme : TimeBasedTheme() {
    override val gradientColors: List<Color>
        get() = listOf(
            Color(0xFF069BE0),
            Color(0xFF12A0E1),
            Color(0xFF57C1EB),
            Color(0xFF8EDBF3),
            Color(0xFFB6EEF9),
            Color(0xFFCFF9FC),
            Color(0xFFD8FEFE)
        )

    override val fabColor: Color
        get() = Color(0xFFFFD800)
    override val cardColor: Color
        get() = Color(0xFFF0FFD6)

    override val textColor: Color
        get() = blackish
    override val backgroundImage: Int
        get() = R.drawable.landscape_1
    override val fabIconColor: Color
        get() = blackish
}
class SunGoingDownTheme() : TimeBasedTheme() {
    override val gradientColors: List<Color>
        get() = listOf(
            Color(0xFF8C2480),
            Color(0xFFCE587D),
            Color(0xFFFF9485),
            Color(0xFFFF9D80),
            Color(0xFFBD73),
            Color(0xFFFFC96F),
        )
    override val fabColor: Color
        get() = Color(0xFFFDD38B)
    override val cardColor: Color
        get() = Color(0xFFFEC86F)
    override val textColor: Color
        get() = Color.White
    override val backgroundImage: Int
        get() = R.drawable.landscape_2
    override val fabIconColor: Color
        get() = blackish
}
class MoonComingUpTheme() : TimeBasedTheme() {
    override val gradientColors: List<Color>
        get() = listOf(
            Color(0xFF3A265E),
            Color(0xF6364BA)
        )
    override val fabColor: Color
        get() = Color(0xFFA8A3C5)
    override val cardColor: Color
        get() = Color(0xFFEBEBEF)
    override val textColor: Color
        get() = Color.White
    override val backgroundImage: Int
        get() = R.drawable.landscape_3
    override val fabIconColor: Color
        get() = Color.White
}
class MoonUpTheme() : TimeBasedTheme() {
    override val gradientColors: List<Color>
        get() = listOf(
            Color(0xFF3A265E),
            Color(0xFF6364BA),
        )
    override val fabColor: Color
        get() = Color(0xFFA8A3C5)
    override val cardColor: Color
        get() = Color(0xFFEBEBEF)
    override val textColor: Color
        get() = Color.White
    override val backgroundImage: Int
        get() = R.drawable.landscape_4
    override val fabIconColor: Color
        get() = Color.White
}
class MoonGoingDownTheme() : TimeBasedTheme() {
    override val gradientColors: List<Color>
        get() = listOf(
            Color(0xFF262F69),
            Color(0xFF3B4791),
            Color(0xFF829BBF),
            Color(0xFFFFB98D)
        )
    override val fabColor: Color
        get() = Color(0xFFA8A3C5)
    override val cardColor: Color
        get() = Color(0xFFEBEBEF)
    override val textColor: Color
        get() = Color.White
    override val backgroundImage: Int
        get() = R.drawable.landscape_5
    override val fabIconColor: Color
        get() = Color.White
}
class SunComingUpTheme() : TimeBasedTheme() {
    override val gradientColors: List<Color>
        get() = listOf(
            Color(0xFFA6D3ED),
            Color(0xFFF9C1C6),
            Color(0xFFFFFFBC)
        )
    override val fabColor: Color
        get() = Color(0xFFFFD800)
    override val cardColor: Color
        get() = Color(0xFFFFFFBC)
    override val textColor: Color
        get() = blackish
    override val backgroundImage: Int
        get() = R.drawable.landscape_6
    override val fabIconColor: Color
        get() = blackish
}
