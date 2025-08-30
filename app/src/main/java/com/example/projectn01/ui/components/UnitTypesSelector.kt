package com.example.projectn01.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun UnitTypesSelector(
    unitTypes: List<String>, selectedUnit: String, onUnitSelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(unitTypes) { item ->
            val isSelected = item == selectedUnit
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))         // يجعل شكل العنصر دائريًا من الزوايا (20dp)
                .background(                             // يغير لون خلفية العنصر حسب ما إذا كان مختارًا أو لا
                    if (isSelected) Color(0xFF0288D1)    // لون أزرق إذا كان العنصر مختارًا
                    else Color.LightGray                 // لون رمادي إذا لم يكن مختارًا
                )
                    .clickable { onUnitSelected(item) }      // عند الضغط عليه، يتم تمرير اسم الوحدة المختارة إلى دالة onUnitSelected
                .padding(
                    horizontal = 16.dp, vertical = 8.dp
                ) // يضيف حشوة داخلية حول النص لجعل العنصر أكبر وأسهل في النقر
            ) {
                Text(
                    text = item,                             // يعرض اسم الوحدة النصي
                    color = if (isSelected) Color.White else Color.Black, // يغير لون النص حسب ما إذا كان مختارًا أو لا
                    fontWeight = FontWeight.Medium           // يجعل وزن الخط متوسطًا (أسمك قليلاً من العادي)
                )
            }
        }
    }
}