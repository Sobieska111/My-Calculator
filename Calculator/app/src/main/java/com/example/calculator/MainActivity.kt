package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.MyBlack
import com.example.calculator.ui.theme.MyJetBlack
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    var input1 by mutableStateOf("")
    var input2 by mutableStateOf("")

    var operator by mutableStateOf("")
    var result by mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CalculatorTheme {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MyBlack)
                        .padding(top = 350.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
                        color = MyJetBlack,
                        tonalElevation = 12.dp

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Transparent),
                            verticalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.Bottom
                            ) {

                                OpButton("C", 80) {
                                    operator = ""
                                    input1 = ""
                                    input2 = ""
                                    result = ""
                                }
                                OpButton("√", 80) { if (input1.isNotEmpty() && input2.isEmpty()) {

                                    result = ""
                                    operator = "√"
                                } }
                                OpButton("%", 80) { if (input1.isNotEmpty() && input2.isEmpty()){

                                    result = ""
                                    operator = "%"
                                } }
                                OpButton("÷", 80) { if (input1.isNotEmpty() && input2.isEmpty()){
                                    result = ""
                                    operator = "÷"
                                } }


                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.Bottom
                            ) {

                                NumButton("7")
                                NumButton("8")
                                NumButton("9")
                                OpButton("×", 80) { if (input1.isNotEmpty() && input2.isEmpty()){
                                    operator = "×"
                                    result = ""

                                } }

                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.Bottom
                            ) {

                                NumButton("4")
                                NumButton("5")
                                NumButton("6")
                                OpButton("-", 80) { if (input1.isNotEmpty() && input2.isEmpty()) {
                                    operator = "-"
                                    result = ""

                                } }

                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.Bottom
                            ) {

                                NumButton("1")
                                NumButton("2" )
                                NumButton("3")
                                OpButton("+", 80) { if (input1.isNotEmpty() && input2.isEmpty()){
                                    operator = "+"
                                    result = ""

                                } }


                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.Bottom
                            ) {

                                NumButton("0")
                                NumButton(".")
                                OpButton("=", 180) { if (input1.isEmpty() || input2.isEmpty()){
                                    result = ""
                                    return@OpButton
                                }else{getResult()} }

                            }

                        }
                    }
                }
                Text(
                    if (result.isEmpty()) input1 + operator + input2 else result,
                    fontSize = 52.sp,
                    color = Color.White,
                    style = TextStyle(lineHeight = 70.sp),
                    modifier = Modifier.padding(top = 140.dp)
                )


            }
        }

    }

    @Composable
    fun OpButton(num: String, width: Int, function: () -> Unit) {
        ElevatedButton(
            onClick = function,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier
                .height(80.dp)
                .width(width.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
        ) {
            Text(text = num, color = Color.Black, fontSize = 32.sp)
        }

    }

    @Composable
    fun NumButton(num: String) {
        Button(
            onClick = { operation(num) },
            modifier = Modifier.size(80.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MyJetBlack)
        ) {
            Text(text = num, color = Color.White, fontSize = 32.sp)
        }

    }

    fun operation(num: String) {
        result =""
        if (operator == "") {
            input1 += num
        } else {
            input2 += num
        }
    }
    fun getResult(){
        val num1 = input1.toLong()
        val num2 = input2.toLong()
        if(operator == "+"){
            result = (num1 + num2).toString()
        }
       else if(operator == "×"){
            result = (num1 * num2).toString()
        }
        else if(operator == "-"){
            result = (num1 - num2).toString()
        }
        else if(operator == "÷"){
            result = (num1 / num2).toString()
        }
        else if(operator == "%"){
            result = (num1 % num2).toString()
        }
        else if(operator == "√"){
            result = sqrt(input1.toDouble()).toString()
        }

        input1 = result
        input2 =""
        operator =""
    }
}