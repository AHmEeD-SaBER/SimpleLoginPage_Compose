package com.example.simpleloginpage.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.simpleloginpage.ui.theme.OrangeMain

@Composable
fun CustomTextField(
    value: String,
    isError:Boolean = false,
    errorMessage: String? = null,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(fontSize = 20.sp, color = Color.Black),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = OrangeMain,
            focusedIndicatorColor = OrangeMain,
            unfocusedLabelColor = OrangeMain,
            focusedLabelColor = OrangeMain,
            unfocusedLeadingIconColor = OrangeMain,
            focusedLeadingIconColor = OrangeMain,
            cursorColor = OrangeMain,
            focusedTrailingIconColor = Color.Black,
            unfocusedTrailingIconColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            errorContainerColor = Color.Transparent,
            errorLeadingIconColor = OrangeMain
        ),
        isError = isError,
    )
    if(isError) {
        Text(
            text = errorMessage ?: "Error",
            style = TextStyle(color = Color.Red, fontSize = 12.sp),
            modifier = Modifier.scale(0.8f)
        )
    }
}