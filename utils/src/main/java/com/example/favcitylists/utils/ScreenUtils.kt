package com.example.favcitylists.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics





object ScreenUtils {
    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun dpToPx(context: Context,dp: Int): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

}