package com.example.androiddevchallenge.composables

import TemperatureWithTime
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.*
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.blackish
import com.example.androiddevchallenge.ui.theme.grayish
import com.example.androiddevchallenge.ui.theme.myStyle
import com.example.androiddevchallenge.ui.theme.myStyleBlackColor

@Composable
fun ExpandedContent(
    convertToFahrenheit: Boolean,
    sunriseTime: String,
    sunsetTime: String,
    windTime: String,
    currentState : ExpandedSheetState,
    modifier: Modifier = Modifier
) {
    val temperatureTypeContentDescription = if(convertToFahrenheit) stringResource(id = R.string.farhenheit) else stringResource(
        id = R.string.celsius
    )
    Box(
        modifier = modifier
            .height(676.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 40.dp, topEnd = 40.dp
                )
            )
            .background(color = Color.White)
    ) {
        if(currentState == ExpandedSheetState.Next7Days)
        {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 36.dp)){
                Text(stringResource(id = R.string.next_7_days),modifier = Modifier.fillMaxWidth().testTag("next7Days"),style = myStyleBlackColor.copy(
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign= TextAlign.Center
                ))

                LazyColumn(modifier = Modifier.padding(vertical =50.dp,horizontal = 25.dp)){
                    items(count = 7){
                        Column(modifier = Modifier.padding(vertical = 10.dp)){
                            val nightTemperature =(10..20).random()
                            val dayTemperature = (21..40).random()
                            val date = getTodaysDate(offset = it+1)
                            val description = stringResource(id = R.string.read_temperature_on_day,date,dayTemperature,temperatureTypeContentDescription,nightTemperature,temperatureTypeContentDescription)
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .semantics(mergeDescendants = true) {
                                    contentDescription =description


                                }){

                                Text(date ,style = myStyleBlackColor.copy(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                ),modifier = Modifier.fillMaxSize(0.50f))
                                Text(
                                    "$dayTemperature°",style = myStyleBlackColor.copy(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Normal ,
                                    textAlign = TextAlign.End
                                ),modifier = Modifier.fillMaxSize(fraction = 0.50f))
                                Text(
                                    "$nightTemperature°",style = myStyleBlackColor.copy(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Normal ,
                                    textAlign = TextAlign.End
                                ),modifier =Modifier.fillMaxSize(fraction = 1f))
                            }
                            Divider()
                        }

                    }
                }
            }

        }else
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                if(currentState == ExpandedSheetState.Today) stringResource(R.string.today) else stringResource(R.string.tomorrow) +" ," + getTodaysDate(offset = 1),
                style = myStyleBlackColor.copy(
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                "India",
                style = myStyleBlackColor.copy(
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(top = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics(mergeDescendants = true) {
                        contentDescription = 27
                            .convertToFahrenheit(convert = convertToFahrenheit)
                            .toString() + "°" + if (convertToFahrenheit) "Fahrenheit" else "Celsius"
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cloudy_small),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.3.dp))
                Text(
                    "${27.convertToFahrenheit(convert = convertToFahrenheit)}",
                    style = myStyleBlackColor.copy(
                        fontSize = 41.sp,
                        fontWeight = FontWeight.Bold,

                        )
                )
                Text(
                    "°",
                    style = myStyleBlackColor.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                )
                Text(
                    if (convertToFahrenheit) "F" else "C",
                    style = myStyleBlackColor.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 25.sp
                    ),

                )
            }
            Text(
                stringResource(R.string.sunny_with_periodic_clouds),
                style = myStyleBlackColor.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Row(modifier = Modifier
                .padding(top = 16.dp)
                .semantics(mergeDescendants = true) {}) {
                val dayText = stringResource(id = R.string.day)+" " + 42.convertToFahrenheit(convertToFahrenheit) + "°"
                val nightText = stringResource(id = R.string.night)+" " + 28.convertToFahrenheit(convertToFahrenheit) + "°"
                Text(
                    dayText,
                    style = myStyleBlackColor.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.semantics {
                        contentDescription = "$dayText$temperatureTypeContentDescription"
                    }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    nightText,
                    style = myStyleBlackColor.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.semantics {
                        contentDescription = "$nightText$temperatureTypeContentDescription"
                    }
                )
            }
            SunriseWindSunsetTime(sunriseTime = sunriseTime, windTime = windTime, sunsetTime =sunsetTime )
            Spacer(modifier = Modifier.height(10.dp))
            TemperatureWithTime(convertToFahrenheit,modifier = Modifier)
            LazyRow(modifier = Modifier.padding(horizontal = 15.dp, vertical = 22.dp)) {
                items(count = 10) {
                    val temperature = (12..40).random()
                    val text  = "${2 * (it - 1) + 6}-${2 * (it - 1) + 8}  "
                    val pmOrAmText =  if ((2 * (it - 1)+8) >= 12) "PM" else "AM"
                    val daysContentDescription = stringResource(id = R.string.read_temperature_on_day,text,pmOrAmText,temperature,temperatureTypeContentDescription)
                    Column(modifier = Modifier.semantics(mergeDescendants = true ) {
                        contentDescription = daysContentDescription
                    }) {

                        Row(
                            modifier = Modifier.padding(end = 26.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.weather_icons_01),
                                contentDescription = null
                            )
                            Text(
                                "$temperature°",
                                style = myStyleBlackColor.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 17.sp
                                ),
                                modifier = Modifier.semantics() {
                                }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 5.dp, top = 3.dp)
                        ) {
                            Text(
                                text ,
                                style = myStyleBlackColor.copy(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                pmOrAmText ,
                                style = myStyleBlackColor.copy(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }
            Text(
                stringResource(R.string.details),
                style = myStyleBlackColor.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(top = 13.dp, bottom = 10.dp)
            )
            Row(modifier = Modifier.semantics(mergeDescendants = true ) {  }) {
                Text(
                    stringResource(R.string.humidity),
                    style = myStyleBlackColor.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = grayish

                    )
                )
                Text(
                    "20%",
                    style = myStyleBlackColor.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                    ),
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            Row(modifier = Modifier
                .padding(top = 10.dp, start = 10.dp)
                .semantics(mergeDescendants = true) { }) {
                Text(
                    stringResource(R.string.uv_index),
                    style = myStyleBlackColor.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = grayish
                    )
                )
                Text(
                    stringResource(R.string.extreme) + ", 11",
                    style = myStyleBlackColor.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                    ),
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}