package com.example.projectn01.constlist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Backspace

object MyLists {
   val  delate = Icons.Default.Backspace
    val botonsConvertarUnit = listOf(
        listOf("7", "8", "9", delate),
        listOf("4", "5", "6", "C"),
        listOf("1", "2", "3", Icons.Default.ArrowUpward),
        listOf("%", "0", ".", Icons.Default.ArrowDownward)
    )

    val ButtonsCalculartorPortrait = listOf(
        listOf("C", "(", ")", "/"),
        listOf("7", "8", "9", "*"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+"),
        listOf("%", "0", ".", "=")
    )

    val ButtonsCalculatorLandscape = listOf(
        listOf("><", "(", "RAD", "√", "C", ")", "%", "/"),
        listOf("!", "sin", "cos", "tan", "7", "8", "9", "*"),
        listOf("mod", "ln", "log", "1/x", "4", "5", "6", "-"),
        listOf("EXP", "^", "x²", "xʸ", "1", "2", "3", "+"),
        listOf("Ans", "|x|", "π", "e", "Rand", "0", ".", "=")
    )

    val unitTypes =
        listOf("Volume", "Mass", "Data", "Time", "Tip", "Area", "Length", "Temperature")

    val volumeUnits = listOf("L", "mL", "m³", "cm³", "gal", "pt", "qt")
    val massUnits = listOf("kg", "g", "mg", "t", "lb", "oz")
    val dataUnits = listOf("b", "B", "KB", "MB", "GB", "TB", "PB")
    val timeUnits = listOf("s", "min", "h", "d", "wk", "mo", "yr")
    val tipUnits = listOf("%", "flat") // flat تعني قيمة ثابتة
    val areaUnits = listOf("m²", "km²", "ft²", "yd²", "ha", "ac")
    val lengthUnits = listOf("m", "cm", "mm", "km", "in", "ft", "yd", "mi")
    val temperatureUnits = listOf("°C", "°F", "K")

}