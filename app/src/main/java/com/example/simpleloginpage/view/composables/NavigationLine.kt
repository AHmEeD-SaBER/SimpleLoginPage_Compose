package com.example.simpleloginpage.view.composables

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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.simpleloginpage.R
import com.example.simpleloginpage.ui.theme.Typography

@Composable
fun NavigationLine(
    onClick: () -> Unit = {},
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
            style = Typography.bodyLarge
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacing_micro)))
        Text(
            trailingValue,
            style = Typography.labelSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .padding(dimensionResource(R.dimen.spacing_minimal))
        )
    }
}