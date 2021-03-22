package com.example.androiddevchallenge.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.blackish
import com.example.androiddevchallenge.ui.theme.myStyle

@Composable
fun UnExpandedContent(sunriseTime: String, sunsetTime: String, windTime: String,modifier : Modifier = Modifier ) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 45.dp, end = 45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        SunriseWindSunsetTime(sunriseTime = sunriseTime, windTime = windTime, sunsetTime = sunsetTime,modifier = Modifier.padding(top = 10.dp))
        Divider(modifier = Modifier.padding(top = 16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        ) {
            Text(
                stringResource(R.string.tomorrow),
                style = myStyle.copy(
                    color = blackish,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Box(
                modifier = Modifier
                    .height(46.dp)
                    .width(1.dp)
                    .background(color = Color(0xFFD0D0D0))
            )
            Text(
                stringResource(R.string.next_7_days),
                style = myStyle.copy(
                    color = blackish,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}