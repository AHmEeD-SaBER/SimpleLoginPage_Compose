package com.example.simpleloginpage.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ImageSurfaces(surface1: Int? = null , surface2: Int? = null, surface3: Int? = null, alignment: Alignment) {
    Box(modifier = Modifier.fillMaxSize()) {
        if(surface1 != null)
            Image(
                painter = painterResource(surface1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.80f)
                    .fillMaxHeight(0.4f)
                    .align(alignment),
                contentScale = ContentScale.FillBounds,
                alignment = alignment
            )
        if(surface2 != null)
            Image(
                painter = painterResource(surface2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.70f)
                    .fillMaxHeight(0.32f)
                    .align(alignment),
                contentScale = ContentScale.FillBounds,
                alignment =alignment

            )
        if(surface3 != null)
            Image(
                painter = painterResource(surface3),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .align(alignment),
                contentScale = ContentScale.FillBounds,
                alignment = alignment
            )
    }
}
