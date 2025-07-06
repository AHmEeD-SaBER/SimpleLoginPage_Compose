package com.example.simpleloginpage.view.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.simpleloginpage.R
import com.example.simpleloginpage.ui.theme.OrangeMain
import com.example.simpleloginpage.ui.theme.Typography
import com.example.simpleloginpage.util.Constants

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
        textStyle = Typography.bodyMedium.copy(color = Color.Black),
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
            text = errorMessage ?: stringResource(R.string.error_default),
            style = Typography.labelMedium.copy(color = Color.Red),
            modifier = Modifier.scale(Constants.ICON_SCALE_SMALLER)
        )
    }
}