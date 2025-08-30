package com.example.projectn01.constlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.projectn01.constlist.MyLists.areaUnits
import com.example.projectn01.constlist.MyLists.dataUnits
import com.example.projectn01.constlist.MyLists.lengthUnits
import com.example.projectn01.constlist.MyLists.massUnits
import com.example.projectn01.constlist.MyLists.temperatureUnits
import com.example.projectn01.constlist.MyLists.timeUnits
import com.example.projectn01.constlist.MyLists.volumeUnits
import kotlin.math.pow

@Composable
fun ConvertResult(
    fromUnit: String, toUnit: String, inputValeu: String, result: (String) -> Unit
) {

    LaunchedEffect(fromUnit, toUnit, inputValeu) {
        val value = inputValeu.toDoubleOrNull()
        if (value == null) {
            result("Invalid input")
            return@LaunchedEffect
        }
        val output = when {

            fromUnit in lengthUnits && toUnit in lengthUnits -> convertLength(value, fromUnit, toUnit)
            fromUnit in massUnits && toUnit in massUnits -> convertMass(value, fromUnit, toUnit)
            fromUnit in volumeUnits && toUnit in volumeUnits -> convertVolume(value, fromUnit, toUnit)
            fromUnit in temperatureUnits && toUnit in temperatureUnits -> convertTemperature(value, fromUnit, toUnit)
            fromUnit in dataUnits && toUnit in dataUnits -> convertData(value, fromUnit, toUnit)
            fromUnit in timeUnits && toUnit in timeUnits -> convertTime(value, fromUnit, toUnit)
            fromUnit in areaUnits && toUnit in areaUnits -> convertArea(value, fromUnit, toUnit)
            else -> "Unsupported conversion"

        }
        result(output)
    }
}

fun convertLength(value: Double, from: String, to: String): String {
    val toMeter = when (from) {
        "km" -> value * 1000
        "m" -> value
        "cm" -> value / 100
        "mm" -> value / 1000
        "in" -> value * 0.0254
        "ft" -> value * 0.3048
        "yd" -> value * 0.9144
        "mi" -> value * 1609.34
        else -> return "Invalid fromUnit"
    }

    val finalValue = when (to) {
        "km" -> toMeter / 1000
        "m" -> toMeter
        "cm" -> toMeter * 100
        "mm" -> toMeter * 1000
        "in" -> toMeter / 0.0254
        "ft" -> toMeter / 0.3048
        "yd" -> toMeter / 0.9144
        "mi" -> toMeter / 1609.34
        else -> return "Invalid toUnit"
    }

    return "%.4f".format(finalValue)
}

fun convertMass(value: Double, from: String, to: String): String {
    val toKg = when (from) {
        "kg" -> value
        "g" -> value / 1000
        "mg" -> value / 1_000_000
        "t" -> value * 1000
        "lb" -> value * 0.453592
        "oz" -> value * 0.0283495
        else -> return "Invalid fromUnit"
    }

    val finalValue = when (to) {
        "kg" -> toKg
        "g" -> toKg * 1000
        "mg" -> toKg * 1_000_000
        "t" -> toKg / 1000
        "lb" -> toKg / 0.453592
        "oz" -> toKg / 0.0283495
        else -> return "Invalid toUnit"
    }

    return "%.4f".format(finalValue)
}

fun convertTemperature(value: Double, from: String, to: String): String {
    val celsius = when (from) {
        "°C" -> value
        "°F" -> (value - 32) * 5 / 9
        "K" -> value - 273.15
        else -> return "Invalid fromUnit"
    }

    val result = when (to) {
        "°C" -> celsius
        "°F" -> celsius * 9 / 5 + 32
        "K" -> celsius + 273.15
        else -> return "Invalid toUnit"
    }

    return "%.2f".format(result)
}

fun convertVolume(value: Double, from: String, to: String): String {
    val toLiter = when (from) {
        "L" -> value
        "mL" -> value / 1000
        "m³" -> value * 1000
        "cm³" -> value / 1000
        "gal" -> value * 3.78541
        "pt" -> value * 0.473176
        "qt" -> value * 0.946353
        else -> return "Invalid fromUnit"
    }

    val final = when (to) {
        "L" -> toLiter
        "mL" -> toLiter * 1000
        "m³" -> toLiter / 1000
        "cm³" -> toLiter * 1000
        "gal" -> toLiter / 3.78541
        "pt" -> toLiter / 0.473176
        "qt" -> toLiter / 0.946353
        else -> return "Invalid toUnit"
    }

    return "%.4f".format(final)
}


fun convertData(value: Double, from: String, to: String): String {
    val toByte = when (from) {
        "b" -> value / 8
        "B" -> value
        "KB" -> value * 1024
        "MB" -> value * 1024 * 1024
        "GB" -> value * 1024 * 1024 * 1024
        "TB" -> value * 1024 * 1024 * 1024 * 1024
        "PB" -> value * 1024 * 1024 * 1024 * 1024 * 1024
        else -> return "Invalid fromUnit"
    }

    val result = when (to) {
        "b" -> toByte * 8
        "B" -> toByte
        "KB" -> toByte / 1024
        "MB" -> toByte / (1024 * 1024)
        "GB" -> toByte / (1024 * 1024 * 1024)
        "TB" -> toByte / (1024.0.pow(4))
        "PB" -> toByte / (1024.0.pow(5))
        else -> return "Invalid toUnit"
    }

    return "%.4f".format(result)
}

fun convertTime(value: Double, from: String, to: String): String {
    val toSeconds = when (from) {
        "s" -> value
        "min" -> value * 60
        "h" -> value * 3600
        "d" -> value * 86400
        "wk" -> value * 604800
        "mo" -> value * 2_629_746  // تقريب شهري (30.44 يوم)
        "yr" -> value * 31_556_952 // تقريبي
        else -> return "Invalid fromUnit"
    }

    val result = when (to) {
        "s" -> toSeconds
        "min" -> toSeconds / 60
        "h" -> toSeconds / 3600
        "d" -> toSeconds / 86400
        "wk" -> toSeconds / 604800
        "mo" -> toSeconds / 2_629_746
        "yr" -> toSeconds / 31_556_952
        else -> return "Invalid toUnit"
    }

    return "%.4f".format(result)
}

fun convertArea(value: Double, from: String, to: String): String {
    val toSqMeters = when (from) {
        "m²" -> value
        "km²" -> value * 1_000_000
        "ft²" -> value * 0.092903
        "yd²" -> value * 0.836127
        "ha" -> value * 10_000
        "ac" -> value * 4046.86
        else -> return "Invalid fromUnit"
    }

    val result = when (to) {
        "m²" -> toSqMeters
        "km²" -> toSqMeters / 1_000_000
        "ft²" -> toSqMeters / 0.092903
        "yd²" -> toSqMeters / 0.836127
        "ha" -> toSqMeters / 10_000
        "ac" -> toSqMeters / 4046.86
        else -> return "Invalid toUnit"
    }

    return "%.4f".format(result)
}

fun convertTip(amount: Double, tipUnit: String, value: Double): String {
    return when (tipUnit) {
        "%" -> "%.2f".format(amount * value / 100)
        "flat" -> "%.2f".format(value)
        else -> "Invalid tip type"
    }
}