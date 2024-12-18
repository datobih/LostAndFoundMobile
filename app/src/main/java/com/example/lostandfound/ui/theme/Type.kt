package com.example.lostandfound.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.lostandfound.R
val Poppins = FontFamily(
    listOf(Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold))
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)


val headlineText = TextStyle(
    fontFamily = Poppins,
    fontWeight =  FontWeight.Bold,
    fontSize = 25.sp,
)

val headlineBigText = TextStyle(
    fontFamily = Poppins,
    fontWeight =  FontWeight.Bold,
    fontSize = 25.sp,
)


val labelTextStyle = TextStyle(
fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    color = Color(0x80000000),
    fontSize = 14.sp,

)




val subText = TextStyle(
    fontFamily = Poppins,
    fontWeight =  FontWeight.Normal,
    color = Color(0xB2000000),
    fontSize = 16.sp,
)

val tinyText = TextStyle(
    fontFamily = Poppins,
    fontWeight =  FontWeight.Bold,
    fontSize = 25.sp,
)

val text22SB = TextStyle(
    fontFamily = Poppins,
    fontWeight =  FontWeight.SemiBold,
    fontSize = 22.sp,
)


val text16SB = TextStyle(
    fontFamily = Poppins,
    fontWeight =  FontWeight.SemiBold,
    fontSize = 16.sp,
)

val text16M = TextStyle(
    fontFamily = Poppins,
    fontWeight =  FontWeight.Medium,
    fontSize = 16.sp,
)

val text18SB = TextStyle(
    fontFamily = Poppins,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold,

)

val text14SB = TextStyle(
    fontFamily = Poppins,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,

    )


val text14Medium = TextStyle(
    fontFamily = Poppins,
    fontSize = 14.sp,
    fontWeight = FontWeight.Medium,

    )

val text12SB = TextStyle(
    fontFamily = Poppins,
    fontSize = 12.sp,
    fontWeight = FontWeight.SemiBold,
    color = Color.Black
    )

val text12N = TextStyle(
    fontFamily = Poppins,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    color = Color.Black
    )


val pinStyle=TextStyle(
    fontFamily= Poppins,
    fontWeight=FontWeight.Bold,
    fontSize = 23.sp,
    color = Color.Black
)

val bottomNavText = TextStyle(
    fontFamily = Poppins,
    fontWeight =  FontWeight.SemiBold,
    fontSize = 12.sp,
    color = Color(  0x40000000)
)
