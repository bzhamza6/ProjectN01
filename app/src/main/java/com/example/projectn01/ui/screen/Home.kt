package com.example.projectn01.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ScreenRotation
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projectn01.constlist.MyLists.ButtonsCalculartorPortrait
import com.example.projectn01.constlist.MyLists.ButtonsCalculatorLandscape
import com.example.projectn01.data.AppDatabase
import com.example.projectn01.data.Operation
import com.example.projectn01.data.OperationViewModel
import com.example.projectn01.data.OperationViewModelFactory
import com.example.projectn01.data.OpirationRepository
import com.example.projectn01.navigation.routs
import com.example.projectn01.ui.components.CalculatorButtonLandskip
import com.example.projectn01.ui.components.CalculatorButtonPortrait
import net.objecthunter.exp4j.ExpressionBuilder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val repository = OpirationRepository(db.historyDao())

    val configiration = LocalConfiguration.current
    val isLandscape = configiration.orientation == Configuration.ORIENTATION_LANDSCAPE



    val viewModelopiration: OperationViewModel = viewModel(
        factory = OperationViewModelFactory(repository)
    )
    val opiration by viewModelopiration.operations.collectAsState(emptyList())


    /*fun calculate() {
        try {
            val res = ExpressionBuilder(expression).build().evaluate()
            result = "= $res"
            viewModelopiration.addOperation(
                Operation(expression = expression,
                    result = res.toString()))
        } catch (e: Exception) {
            result = "خطأ"
        }
    }*/


    fun calculate(ans: Double = 0.0, isDegree: Boolean = false) {
        try {
            // استبدال الرموز بالشكل المناسب لـ exp4j
            var exp = expression.replace("x²", "^2").replace("xʸ", "^").replace("√", "sqrt")
                .replace("π", Math.PI.toString()).replace("e", Math.E.toString())
                .replace("Ans", ans.toString()).replace("|x|", "abs").replace("mod", "%")
                .replace("!", "fact") // factorial

            // factorial function
            val factorial = object : net.objecthunter.exp4j.function.Function("fact", 1) {
                override fun apply(vararg args: Double): Double {
                    val n = args[0].toInt()
                    var res = 1.0
                    for (i in 1..n) res *= i
                    return res
                }
            }

            // sin, cos, tan (مع دعم الدرجات)
            val sinFunc = object : net.objecthunter.exp4j.function.Function("sin", 1) {
                override fun apply(vararg args: Double): Double {
                    val value = if (isDegree) Math.toRadians(args[0]) else args[0]
                    return kotlin.math.sin(value)
                }
            }
            val cosFunc = object : net.objecthunter.exp4j.function.Function("cos", 1) {
                override fun apply(vararg args: Double): Double {
                    val value = if (isDegree) Math.toRadians(args[0]) else args[0]
                    return kotlin.math.cos(value)
                }
            }
            val tanFunc = object : net.objecthunter.exp4j.function.Function("tan", 1) {
                override fun apply(vararg args: Double): Double {
                    val value = if (isDegree) Math.toRadians(args[0]) else args[0]
                    return kotlin.math.tan(value)
                }
            }

            // بناء وتقييم
            val res =
                ExpressionBuilder(exp).function(factorial).function(sinFunc)
                    .function(cosFunc).function(tanFunc).build().evaluate()

            result = if (res.isNaN()) {
                "خطأ"
            } else {
                "= $res"
            }

            // حفظ في التاريخ
            if (!res.isNaN()) {
                viewModelopiration.addOperation(
                    Operation(expression = expression, result = res.toString())
                )
            }
        } catch (e: Exception) {
            result = "خطأ"
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = expression,
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.End
        )
        Text(
            text = result,
            fontSize = 40.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            textAlign = TextAlign.End
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                IconButton(onClick = { showSheet = true }) {
                    Icon(Icons.Default.History, contentDescription = "History", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { navController.navigate(routs.UNIT_CONVERTER) }) {
                    Icon(
                        Icons.Default.SwapHoriz,
                        contentDescription = "Converter",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { /* TODO: Rotate calculator to scientific */ }) {
                    Icon(
                        Icons.Default.ScreenRotation,
                        contentDescription = "Scientific",
                        tint = Color.White
                    )
                }
            }

            IconButton(onClick = {
                if (expression.isNotEmpty()) {
                    expression = expression.dropLast(1)
                }
            }) {
                Icon(Icons.Default.Backspace, contentDescription = "Delete", tint = Color.White)
            }
        }



        val buttons = if (isLandscape) {
            ButtonsCalculatorLandscape
        } else {
            ButtonsCalculartorPortrait
        }

        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEach { symbol ->
                    if (isLandscape) {
                        CalculatorButtonLandskip(symbol) {
                            when (symbol) {
                                "=" -> calculate()
                                "C" -> {
                                    expression = ""
                                    result = ""
                                }

                                "%" -> expression += "/100"
                                "√" -> expression += "√("
                                "x²" -> expression += "^2"
                                "xʸ" -> expression += "^"
                                "π" -> expression += "π"
                                "e" -> expression += "e"
                                "sin" -> expression += "sin("
                                "cos" -> expression += "cos("
                                "tan" -> expression += "tan("
                                "log" -> expression += "log("
                                "ln" -> expression += "ln("
                                "!" -> expression += "!"
                                else -> expression += symbol
                            }
                        }
                    } else {
                        CalculatorButtonPortrait(symbol) {
                            when (symbol) {
                                "=" -> calculate()
                                "C" -> {
                                    expression = ""
                                    result = ""
                                }
                                "%" -> expression += "/100"
                                "(" -> expression += "("
                                ")" -> expression += ")"
                                "/" -> expression += "/"
                                "*" -> expression += "*"
                                "-" -> expression += "-"
                                "+" -> expression += "+"
                                "." -> expression += "."
                                else -> expression += symbol  // للأرقام "0" .. "9"
                            }
                        }
                    }
                }
            }
        }
        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false }, sheetState = sheetState
            ) {
                Column(
                    Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("History", style = MaterialTheme.typography.titleLarge)
                        TextButton(onClick = { viewModelopiration.clearOperations() }) {
                            Text("Clear", color = Color.Red)
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    LazyColumn {
                        items(opiration) { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    TextButton(onClick = { expression += item.expression }) {
                                        Text(
                                            item.expression
                                        )
                                    }
                                    TextButton(onClick = { expression += item.result }) { Text("=" + item.result) }
                                }
                                IconButton(onClick = { viewModelopiration.deleteOperation(item) }) {
                                    Icon(
                                        Icons.Default.Backspace,
                                        contentDescription = "Delete item",
                                        tint = Color.Gray
                                    )
                                }
                            }


                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewCalculator() {

}