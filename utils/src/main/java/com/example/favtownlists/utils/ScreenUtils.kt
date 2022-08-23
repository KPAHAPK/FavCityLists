package com.example.favtownlists.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics





object ScreenUtils {
    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun dpToPx(context: Context,dp: Int): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun pxToDp(context: Context, px: Int): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }
}