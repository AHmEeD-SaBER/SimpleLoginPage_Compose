package com.example.simpleloginpage.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpleloginpage.ui.theme.OrangeMain

@Composable
fun CustomBtn(
    value: String,
    onCLick: () -> Unit,
) {
    Button(
        onClick = onCLick,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        colors = ButtonColors(
            containerColor = OrangeMain,
            contentColor = Color.White,
            disabledContainerColor = OrangeMain.copy(alpha = 0.5f),
            disabledContentColor = Color.White.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp)
    )
    {

        Text(
            value,
            style = TextStyle(fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight(800))
        )
    }
}