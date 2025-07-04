package com.example.simpleloginpage.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavigationLine(
    onClick: () -> Unit = { /* Default no-op */ },
    leadingValue: String,
    trailingValue: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            leadingValue,
            style = TextStyle(fontSize = 18.sp,fontWeight = FontWeight(600))
        )
        Spacer(modifier = Modifier.width(1.dp))
        Text(
            trailingValue,
            style = TextStyle(
                fontSize = 18.sp,
                color = Color(0xffffc122),
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight(600),
            ),
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .padding(5.dp)
        )
    }
}