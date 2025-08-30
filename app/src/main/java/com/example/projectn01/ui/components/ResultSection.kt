package com.example.projectn01.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ResultSection(
    availableUnits: List<String>,
    selectedToUnit: String,
    onToUnitSelected: (String) -> Unit,
    result: String
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = true }
            .padding(12.dp)) {

            Row {
                Text(text = selectedToUnit)
                Spacer(modifier = Modifier.width(2.dp))
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }


            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {

                availableUnits.forEach { unit ->
                    DropdownMenuItem(text = {
                        Text(unit) },
                        onClick = {
                        onToUnitSelected(unit)
                        isExpanded = false
                    })
                }

            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        // عرض النتيجة
        TextField(
            value = result,
            onValueChange = {},
            placeholder = { Text("النتيجة") },
            readOnly = true, // هذا يمنع ظهور الكيبورد
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = 24.sp
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Gray,
                disabledIndicatorColor = Color.LightGray,
                cursorColor = Color.Black,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            suffix = {Text(selectedToUnit)}

        )


    }
}