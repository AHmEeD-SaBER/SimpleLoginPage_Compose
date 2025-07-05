package com.example.simpleloginpage.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit = { },
    logo: Int,
    actionIcon: Int
) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(logo),
                contentDescription = "Logo",
                modifier = Modifier.size(50.dp).scale(1f),
            )
        },
        actions = {
            IconButton(onClick = onMenuClick, modifier = Modifier.size(70.dp).scale(1.2f)) {
                Image(
                    painter = painterResource(actionIcon),
                    contentDescription = "Menu",
                    modifier = Modifier.scale(1.5f)
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}
