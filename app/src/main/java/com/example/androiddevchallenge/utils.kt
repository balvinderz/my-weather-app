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

import com.example.androiddevchallenge.ui.theme.MoonComingUpTheme
import com.example.androiddevchallenge.ui.theme.MoonGoingDownTheme
import com.example.androiddevchallenge.ui.theme.MoonUpTheme
import com.example.androiddevchallenge.ui.theme.SunComingUpTheme
import com.example.androiddevchallenge.ui.theme.SunGoingDownTheme
import com.example.androiddevchallenge.ui.theme.SunriseTheme
import com.example.androiddevchallenge.ui.theme.TimeBasedTheme
import java.util.Calendar
import java.util.Date

fun celsiusToF(celsius: Float): Float {
    return celsius * 1.8f + 32.00f
}
fun fahrenheitToC(fahrenheit: Float): Float {
    return (fahrenheit - 32.00f) / 1.8f
}
enum class TemperatureType {
    Celsius,
    Fahrenheit
}
enum class ExpandedSheetState{
    Today,
    Tomorrow,
    Next7Days
}
fun generateRandomTime(start: Int, end: Int): String {
    val hour: Int = (start..end).random()
    val minute: Int = (10..59).random()
    if (hour <10)
        return "0$hour:$minute"

    return "$hour:$minute"
}
fun getTodaysDate(offset : Int = 0): String {
    val months = listOf("January", "February", "March", "May", "June", "July", "August", "September", "October", "November", "December")
    val calendar: Calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_MONTH,offset)
    return "${calendar.get(Calendar.DAY_OF_MONTH)} ${months[calendar.get(Calendar.MONTH)]} ${calendar.get(Calendar.YEAR)}"
}
fun getCurrentTime(): String {

    val calendar: Calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val stringHour = if (hour <10) "0$hour" else hour.toString()
    val minutes = calendar.get(Calendar.MINUTE)
    val stringMinutes = if (minutes <10) "0$minutes" else minutes.toString()
    return "$stringHour:$stringMinutes"
}
fun getTheme(): TimeBasedTheme {
    val calendar: Calendar = Calendar.getInstance()
    when (calendar.get(Calendar.HOUR_OF_DAY)) {
        in 4..7 -> return SunComingUpTheme()
        in 8..16 -> return SunriseTheme()
        in 17..19 -> return SunGoingDownTheme()
        in 20..22 -> return MoonComingUpTheme()
        in 2..23 -> return MoonUpTheme()
        in 0..3 -> return MoonGoingDownTheme()
    }
    return SunComingUpTheme()
}
