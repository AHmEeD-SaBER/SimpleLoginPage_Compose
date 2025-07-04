package com.example.simpleloginpage.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.simpleloginpage.R

@Composable
fun CustomAppBar(
    barModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick: () -> Unit = { /* Default no-op */ },
    logo: Int,
    actionIcon: Int
) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = barModifier
    ){
        Image(
            painter = painterResource(logo),
            contentDescription = "Logo",
            modifier = iconModifier,
            alignment = Alignment.CenterStart,
        )
        Image(
            painter = painterResource(actionIcon),
            contentDescription = "Action Menu",
            modifier = iconModifier.scale(0.7f)
                .clickable {
                    onClick()
                },
            alignment = Alignment.CenterStart,
        )


    }
}