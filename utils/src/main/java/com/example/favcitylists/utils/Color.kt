package com.example.favcitylists.utils

import androidx.annotation.ColorRes
import androidx.annotation.StringRes


data class Color(@StringRes val colorName: Int, @ColorRes val color: Int)

val listOfColors = listOf(
    Color(R.string.color_blue, R.color.blue),
    Color(R.string.color_red, R.color.red),
    Color(R.string.color_yellow, R.color.yellow),
    Color(R.string.color_green, R.color.green),
)