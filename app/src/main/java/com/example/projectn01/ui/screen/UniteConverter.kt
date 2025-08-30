@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.projectn01.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projectn01.constlist.ConvertResult
import com.example.projectn01.constlist.MyLists.areaUnits
import com.example.projectn01.constlist.MyLists.botonsConvertarUnit
import com.example.projectn01.constlist.MyLists.dataUnits
import com.example.projectn01.constlist.MyLists.delate
import com.example.projectn01.constlist.MyLists.lengthUnits
import com.example.projectn01.constlist.MyLists.massUnits
import com.example.projectn01.constlist.MyLists.temperatureUnits
import com.example.projectn01.constlist.MyLists.timeUnits
import com.example.projectn01.constlist.MyLists.tipUnits
import com.example.projectn01.constlist.MyLists.unitTypes
import com.example.projectn01.constlist.MyLists.volumeUnits
import com.example.projectn01.ui.components.ConvertorButton
import com.example.projectn01.ui.components.ResultSection
import com.example.projectn01.ui.components.UnitInputSection
import com.example.projectn01.ui.components.UnitTypesSelector
import com.example.projectn01.ui.theme.ProjectN01Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniteConverter(navController: NavHostController) {

    var selectedUnit by remember { mutableStateOf(unitTypes.first()) }
    var valueToConvert by remember { mutableStateOf("0") }
    var result by remember { mutableStateOf("0") }

    val availableUnits = when (selectedUnit) {
        "Volume" -> volumeUnits
        "Mass" -> massUnits
        "Data" -> dataUnits
        "Time" -> timeUnits
        "Tip" -> tipUnits
        "Area" -> areaUnits
        "Length" -> lengthUnits
        "Temperature" -> temperatureUnits
        else -> {
            listOf("--", "--", "--", "--")
        }
    }

    var fromUnit by remember { mutableStateOf("") }
    var toUnit by remember { mutableStateOf("") }

    LaunchedEffect(selectedUnit) {
        fromUnit = availableUnits.first()
        toUnit = availableUnits.first()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010)), // نفس خلفية Home
        topBar = {
            TopAppBar(title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            contentDescription = "back to home",
                            tint = Color.White
                        )
                    }
                    Text("Unit Converter")
                }
            })
        }) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp) // مسافة داخلية إضافية
            , verticalArrangement = Arrangement.Bottom
        ) {

            // ✅ الجزء الأول: اختيار الوحدة الأصلية + إدخال القيمة
            UnitInputSection(
                availableUnits = availableUnits,
                selectedUnit = fromUnit,
                onUnitSelected = { fromUnit = it },
                inputValue = valueToConvert,
                onInputChanged = { valueToConvert = it })

            // ✅ الجزء الثاني: اختيار الوحدة الهدف + عرض النتيجة
            ResultSection(
                availableUnits = availableUnits,
                selectedToUnit = toUnit,
                onToUnitSelected = { toUnit = it },
                result = result
            )

            UnitTypesSelector(
                unitTypes = unitTypes,
                selectedUnit = selectedUnit,
                onUnitSelected = { selectedUnit = it })

            botonsConvertarUnit.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { button ->
                        ConvertorButton(button = button) {
                            when (button) {
                                "C" -> {
                                    valueToConvert = "0"
                                    result = "0"
                                }

                                delate -> {
                                    if (valueToConvert.isNotEmpty()) {

                                        valueToConvert = valueToConvert.dropLast(1)
                                        if (valueToConvert ==""){
                                            valueToConvert = "0"
                                        }
                                    }
                                }

                                is String -> valueToConvert += button
                            }
                        }
                    }
                }

            }

            ConvertResult(fromUnit, toUnit, inputValeu = valueToConvert, result = { result = it })

        }
    }
}


//دالة عريض التصميم "Preview"
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PreviewUnitConverter() {
    // استخدم rememberNavController لتفادي الكراش
    ProjectN01Theme {
        UniteConverter(navController = rememberNavController())
    }
}