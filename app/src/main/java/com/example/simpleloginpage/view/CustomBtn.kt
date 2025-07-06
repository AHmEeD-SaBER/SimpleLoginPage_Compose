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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.simpleloginpage.R
import com.example.simpleloginpage.ui.theme.OrangeMain
import com.example.simpleloginpage.ui.theme.Typography

@Composable
fun CustomBtn(
    value: String,
    onCLick: () -> Unit,
) {
    Button(
        onClick = onCLick,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.button_height)),
        colors = ButtonColors(
            containerColor = OrangeMain,
            contentColor = Color.White,
            disabledContainerColor = OrangeMain.copy(alpha = 0.5f),
            disabledContentColor = Color.White.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
        contentPadding = PaddingValues(0.dp)
    )
    {
        Text(
            value,
            style = Typography.labelLarge.copy(color = Color.Black)
        )
    }
}
