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
    baseSurface: Int? = null,
    topSurface: Int? = null,
    darkenSurface: Int? = null,
    topDarkenSurface: Int? = null,
    maxHeight: Float = Constants.BG_LARGE_HEIGHT,
    alignment: Alignment
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (baseSurface != null)
            Image(
                painter = painterResource(baseSurface),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(Constants.BG_SURFACE_WIDTH_LARGE)
                    .fillMaxHeight(maxHeight)
                    .align(alignment),
                contentScale = ContentScale.FillBounds,
                alignment = alignment
            )
        if (topSurface != null)
            Image(
                painter = painterResource(topSurface),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(Constants.BG_SURFACE_WIDTH_MEDIUM)
                    .fillMaxHeight(Constants.BG_SURFACE_HEIGHT_SMALL)
                    .align(alignment),
                contentScale = ContentScale.FillBounds,
                alignment = alignment

            )
        if (darkenSurface != null)
            Image(
                painter = painterResource(darkenSurface),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(Constants.BG_SURFACE_HEIGHT)
                    .align(alignment),
                contentScale = ContentScale.FillBounds,
                alignment = alignment
            )
        if (topDarkenSurface != null)
            Image(
                painter = painterResource(topDarkenSurface),
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
