package com.example.simpleloginpage.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(fontSize = 20.sp, color = Color.Black),
        leadingIcon = leadingIcon,
        trailingIcon = if (isPassword) {
            {
                Icon(
                    imageVector = Icons.Filled.VisibilityOff,
                    contentDescription = "Show Password",
                    modifier = Modifier.scale(0.7f)
                )
            }
        } else null,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color(0xffffc122),
            focusedIndicatorColor = Color(0xffffc122),
            unfocusedLabelColor = Color(0xffffc122),
            focusedLabelColor = Color(0xffffc122),
            unfocusedLeadingIconColor = Color(0xffffc122),
            focusedLeadingIconColor = Color(0xffffc122),
            cursorColor = Color(0xffffc122),
            focusedTrailingIconColor = Color.Black,
            unfocusedTrailingIconColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}