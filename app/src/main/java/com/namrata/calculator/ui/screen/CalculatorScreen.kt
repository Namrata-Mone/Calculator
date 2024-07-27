package com.namrata.calculator.ui.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namrata.calculator.viewmodel.CalculatorViewModel

val buttonList= listOf(
    "AC","(",")","/",
    "7","8","9","*",
    "4","5","6","-",
    "1","2","3","+",
    "C","0",".","=")
@Composable
fun CalculatorScreen(modifier: Modifier = Modifier,
                     calculatorViewModel: CalculatorViewModel)
{

    val equationText = calculatorViewModel.equationText.observeAsState()
    val resultText = calculatorViewModel.resultText.observeAsState()

    Box(modifier=modifier) {
        Column(modifier=Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text=equationText.value?:"",
                style = TextStyle(
                    fontSize=30.sp,
                    textAlign = TextAlign.End
                ),
                color = if(isSystemInDarkTheme()){
                    Color(0xffffffff)
                }else{
                    Color(0xff1C1C1C)
                },
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text=resultText.value?:"0",
                style = TextStyle(
                    fontSize=60.sp,
                    textAlign = TextAlign.End
                ),
                color = if(isSystemInDarkTheme()){
                    Color(0xffffffff)
                }else{
                    Color(0xff1C1C1C)
                },
                maxLines = 2
            )
            Spacer(modifier = Modifier.weight(1f))
            LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                items(buttonList){
                    CalculatorButton(it,
                        onClick={
                            calculatorViewModel
                                .onButtonClick(it)
                        }
                        )
                }
            }
        }
    }


}

@Composable
fun CalculatorButton(button: String,onClick:()->Unit) {
    Box(modifier = Modifier.padding(8.dp)){
    Row (
        Modifier.fillMaxWidth(),
        horizontalArrangement = SpaceEvenly
    ){
        FloatingActionButton(
            onClick =onClick,
            Modifier.size(80.dp),
            shape = CircleShape,
            contentColor = Color.White,
            containerColor = getColor(button)
        ) {
            Text(text = button,
                color = getTextColor(button),
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

    }
}

fun getColor(button:String):Color{
    return when(button){
        "/","*","-","+","=" -> Color(0xffFF9500)
        "AC","C","(",")" -> Color(0xffD4D4D2)
        else -> Color(0xff505050)
    }
}
fun getTextColor(button:String):Color{
    return when(button){
        "/","*","-","+","=" -> Color(0xffffffff)
        "AC","C","(",")" -> Color(0xff1C1C1C)
        else -> Color(0xffffffff)
    }
}