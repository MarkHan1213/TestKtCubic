package com.mark.testktcubic

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager

object Utils {

    /**
     * 获得屏幕高度
     */
    fun loadWinSize(context: Context, winSize: Point) {
        val wm: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        winSize.x = outMetrics.widthPixels
        winSize.y = outMetrics.heightPixels
    }

    fun judgeCircleArea(src: Point, dst: Point, r: Float): Boolean {
        return disPos2d(src.x.toFloat(), src.y.toFloat(), dst.x.toFloat(), dst.y.toFloat()) <= r
    }

    fun disPos2d(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return Math.sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).toDouble()).toFloat()
    }
}