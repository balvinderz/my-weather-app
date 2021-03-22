import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.convertToFahrenheit
import com.example.androiddevchallenge.getCurrentTime
import com.example.androiddevchallenge.ui.theme.myStyleBlackColor

@Composable
fun TemperatureWithTime(convertToFahrenheit: Boolean,modifier: Modifier = Modifier ) {
    val temperatureTypeString = if(convertToFahrenheit) stringResource(id = R.string.farhenheit) else stringResource(
        id = R.string.celsius
    )

    val contentDescriptionForCenterElement : String = stringResource(id = R.string.read_temperature,37.convertToFahrenheit(convertToFahrenheit),temperatureTypeString,
        getCurrentTime())
    Box(modifier = modifier.size(106.dp))
    {


        Canvas(modifier = Modifier.size(106.dp)) {
            withTransform({
                translate(left = 11 / 5F)
                rotate(degrees = 52f)
            }) {
                drawArc(
                    color = Color(0xFFEBEBEB),
                    startAngle = 70F, sweepAngle = 290F, useCenter = false , topLeft = Offset.Zero, style = Stroke(
                        width = 5f
                    ) )

            }
        }

        Column(modifier = Modifier.semantics(mergeDescendants = true ) {
            contentDescription  =contentDescriptionForCenterElement
        }) {
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End)
            {
                Image(painter =  painterResource(id = R.drawable.sun),contentDescription = null
                                )
            }
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically){
                Image(painter = painterResource(id = R.drawable.therometer_small),contentDescription = null
                                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("${37.convertToFahrenheit(convertToFahrenheit)}°",style = myStyleBlackColor.copy(
                    fontSize = 33.sp,
                    fontWeight = FontWeight.Normal
                ))
                Text(if(convertToFahrenheit)"F" else "C",style = myStyleBlackColor.copy(
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Normal
                ))
            }
            Text(
                getCurrentTime(),style =  myStyleBlackColor.copy(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                ),modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 13.dp))
        }

    }




}