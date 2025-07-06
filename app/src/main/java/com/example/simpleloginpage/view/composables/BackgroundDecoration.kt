package com.example.simpleloginpage.view.composables

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
import com.example.simpleloginpage.util.Constants

@Composable
fun BackgroundDecoration(
    surface1: Int? = null,
    surface2: Int? = null,
    surface3: Int? = null,
    surface4: Int? = null,
    maxHeight: Float = Constants.BG_LARGE_HEIGHT,
    alignment: Alignment
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (surface1 != null)
            Image(
                painter = painterResource(surface1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(Constants.BG_SURFACE_WIDTH_LARGE)
                    .fillMaxHeight(maxHeight)
                    .align(alignment),
                contentScale = ContentScale.FillBounds,
                alignment = alignment
            )
        if (surface2 != null)
            Image(
                painter = painterResource(surface2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(Constants.BG_SURFACE_WIDTH_MEDIUM)
                    .fillMaxHeight(Constants.BG_SURFACE_HEIGHT_SMALL)
                    .align(alignment),
                contentScale = ContentScale.FillBounds,
                alignment = alignment

            )
        if (surface3 != null)
            Image(
                painter = painterResource(surface3),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(Constants.BG_SURFACE_HEIGHT)
                    .align(alignment),
                contentScale = ContentScale.FillBounds,
                alignment = alignment
            )
        if (surface4 != null)
            Image(
                painter = painterResource(surface4),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(Constants.BG_SURFACE_WIDTH_SMALL)
                    .fillMaxHeight(Constants.BG_SURFACE_HEIGHT)
                    .align(alignment),
                contentScale = ContentScale.FillBounds,
                alignment = alignment
            )
    }
}
