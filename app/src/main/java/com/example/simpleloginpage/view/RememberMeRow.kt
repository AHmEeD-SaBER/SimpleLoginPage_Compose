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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.simpleloginpage.R
import com.example.simpleloginpage.ui.theme.OrangeMain
import com.example.simpleloginpage.ui.theme.Typography
import com.example.simpleloginpage.util.Constants


@Composable
fun RememberMeRow(
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
                modifier = Modifier
                    .scale(Constants.ICON_SCALE_SMALL)
                    .size(dimensionResource(R.dimen.checkbox_size))
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacing_tiny)))
            Text(
                stringResource(R.string.remember_me),
                style = Typography.bodySmall
            )
        }
        Text(
            stringResource(R.string.forgot_password),
            style = Typography.bodySmall,
            modifier = Modifier.clickable { onForgotPasswordClick() }
        )
    }
}