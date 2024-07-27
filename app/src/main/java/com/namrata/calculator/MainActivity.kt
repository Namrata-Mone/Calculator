package com.namrata.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.namrata.calculator.ui.screen.CalculatorScreen
import com.namrata.calculator.ui.theme.CalculatorTheme
import com.namrata.calculator.viewmodel.CalculatorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calculatorViewModel=ViewModelProvider(this)[CalculatorViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold( modifier = Modifier.fillMaxSize(),
                    containerColor =
                    if(isSystemInDarkTheme()){
                        Color(0xff1C1C1C)
                    }else{
                        Color(0xffffffff)
                    }
                ) { innerPadding ->
                    CalculatorScreen(Modifier.padding(innerPadding),calculatorViewModel)
                }
            }
        }
    }
}