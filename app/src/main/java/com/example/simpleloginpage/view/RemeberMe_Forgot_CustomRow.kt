package com.example.simpleloginpage.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpleloginpage.ui.theme.OrangeMain


@Composable
fun RememberMe_Forgot_CustomRow(
    rememberMe: Boolean = false,
    onRememberMeChange: (Boolean) -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { onRememberMeChange(it) },
                colors = CheckboxDefaults.colors(
                    checkedColor = OrangeMain,
                    uncheckedColor = OrangeMain,
                    checkmarkColor = Color.Black
                ),
                modifier = Modifier.scale(0.7f).size(10.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "Remember Me",
                style = TextStyle(fontSize = 15.sp)
            )
        }
        Text(
            "Forgot Password?",
            style = TextStyle(fontSize = 15.sp),
            modifier = Modifier.clickable { onForgotPasswordClick() }
        )
    }
}