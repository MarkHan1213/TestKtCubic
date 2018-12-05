package com.mark.testktcubic

import android.graphics.Path
import android.graphics.Point

object HelpPath {

    /**
     * 获取表格的path,带虚线
     */
    fun gridPath(step: Int, winSize: Point): Path {
        val path = Path()

        for (i in 0 until winSize.y / step + 1) {
            path.moveTo(0f, (step * i).toFloat())
            path.lineTo(winSize.x.toFloat(), (step * i).toFloat())
        }
        for (i in 0 until winSize.x / step + 1) {
            path.moveTo((step * i).toFloat(), 0F)
            path.lineTo((step * i).toFloat(), winSize.y.toFloat())
        }
        return path
    }


    /**
     * 坐标系路径
     */
    fun cooPath(coo: Point, windowSize: Point): Path {
        val path = Path()

        path.moveTo(coo.x.toFloat(), coo.y.toFloat())
        path.lineTo(windowSize.x.toFloat(), coo.y.toFloat())

        path.moveTo(coo.x.toFloat(), coo.y.toFloat())
        path.lineTo((coo.x - windowSize.x).toFloat(), coo.y.toFloat())

        path.moveTo(coo.x.toFloat(), coo.y.toFloat())
        path.lineTo(coo.x.toFloat(), (coo.y - windowSize.y).toFloat())

        path.moveTo(coo.x.toFloat(), coo.y.toFloat())
        path.lineTo(coo.x.toFloat(), windowSize.y.toFloat())

        return path
    }
}