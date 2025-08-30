package com.example.projectn01.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ConvertorButton(button: Any, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(80.dp)
            .clip(CircleShape)
            .background(Color(0xFF37474F))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        when (button) {
            is String -> Text(
                text = button, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White
            )

            is ImageVector -> Icon(
                imageVector = button,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun CalculatorButtonPortrait(symbol: String, onClick: () -> Unit) {

    val backgroundColor = when (symbol) {
        "=" -> Color(0xFFFF9800) // برتقالي
        "C" -> Color(0xFFB71C1C) // أحمر غامق
        "/", "*", "-", "+", "%" -> Color(0xFF37474F) // رمادي غامق للعمليات
        else -> Color(0xFF212121) // رمادي داكن للأرقام
    }

    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(80.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

    }
}

@Composable
fun CalculatorButtonLandskip(symbol: String, onClick: () -> Unit) {
    val backgroundColor = when (symbol) {
        "=" -> Color(0xFFFF9800) // برتقالي
        else -> Color(0xFF212121) // رمادي داكن للأرقام
    }
    val TextColor = when (symbol) {
        "C" -> Color(0xFFB71C1C) // أحمر غامق
        else -> Color(0xFFFFFFFF) // رمادي داكن للأرقام
    }

    Box(
        modifier = Modifier
            .padding(3.dp)
            .size(width = 70.dp, height = 40.dp) // أصغر وأعرض قليلاً
            .clip(RoundedCornerShape(12.dp)) // مستطيل بحواف دائرية
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            fontSize = 24.sp, // تكبير/تصغير حسب طول الرمز
            fontWeight = FontWeight.Bold,
            color = TextColor
        )
    }
}


