package com.example.simpleloginpage.util

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.simpleloginpage.R

object Fonts {
    val Inter = FontFamily(
        Font(R.font.inter_28pt_bold, FontWeight.Bold),
        Font(R.font.inter_28pt_black, FontWeight.Black),
        Font(R.font.inter_28pt_light, FontWeight.ExtraBold),
        Font(R.font.inter_18pt_light, FontWeight.Light),
        Font(R.font.inter_p18t_black, FontWeight.Black),
    )
}