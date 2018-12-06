package com.mark.testktcubic

import android.content.Context
import android.graphics.*

object HelpDraw {

    var isDraw = true

    /**
     * 绘制picture
     */
    fun draw(canvas: Canvas, vararg pictures: Picture) {
        if (!isDraw) {
            return
        }

        for (picture in pictures) {
            picture.draw(canvas)
        }
    }

    /**
     * 绘制网格
     */
    fun getwindowSize(context: Context): Point {
        val point = Point()
        Utils.loadWinSize(context, point)
        return point
    }

    /**
     * 绘制网格
     */
    fun getGrid(context: Context): Picture {
        return getGrid(getwindowSize(context))
    }

    fun getGrid(windowSize: Point): Picture {
        val picture = Picture()
        val recording = picture.beginRecording(windowSize.x, windowSize.y)

        val paint = getHelpPaint()
        recording.drawPath(HelpPath.gridPath(50, windowSize), paint)
        return picture
    }

    fun getHelpPaint(): Paint {
        return getHelpPaint(Color.GRAY)
    }

    /**
     * 绘制点集
     */
    fun drawPos(canvas: Canvas, paint: Paint, vararg points: Point) {
        for (point in points) {
            canvas.drawPoint(point.x.toFloat(), point.y.toFloat(), paint)
        }
    }

    /**
     * 绘制点集
     */
    fun drawPos(canvas: Canvas, paint: Paint, vararg points: PointF) {
        for (point in points) {
            canvas.drawPoint(point.x, point.y, paint)
        }
    }

    fun drawLines(canvas: Canvas, paint: Paint, vararg points: Point) {
        for (i in 0 until points.size step 2) {
            canvas.drawLine(
                points[i].x.toFloat(),
                points[i].y.toFloat(),
                points[i + 1].x.toFloat(),
                points[i + 1].y.toFloat(),
                paint
            )
        }
    }

    fun drawLines(canvas: Canvas, paint: Paint, vararg points: PointF) {
        for (i in 0 until points.size step 2) {
            canvas.drawLine(
                points[i].x,
                points[i].y,
                points[i + 1].x,
                points[i + 1].y,
                paint
            )
        }
    }


    fun getHelpPaint(color: Int): Paint {
        //初始化网格画笔
        val paint = Paint()
        paint.strokeWidth = 2F
        paint.color = color
        paint.textSize = 50f
        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
        //设置虚线效果
        paint.pathEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        return paint
    }

    fun getCoo(context: Context, coo: Point): Picture {
        return getCoo(coo, getwindowSize(context))
    }

    fun getCoo(coo: Point, windowSize: Point): Picture {
        val picture = Picture()
        val recording = picture.beginRecording(windowSize.x, windowSize.y)
        //初始化网格画笔
        val paint = Paint()
        paint.strokeWidth = 4F
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        //设置虚线效果
        paint.pathEffect = null

        //绘制直线
        with(recording) {
            drawPath(HelpPath.cooPath(coo, windowSize), paint)
            //左箭头
            drawLine(
                windowSize.x.toFloat(),
                coo.y.toFloat(),
                (windowSize.x - 40).toFloat(),
                (coo.y - 20).toFloat(),
                paint
            )
            drawLine(
                windowSize.x.toFloat(),
                coo.y.toFloat(),
                (windowSize.x - 40).toFloat(),
                (coo.y + 20).toFloat(),
                paint
            )
            //下箭头
            drawLine(
                coo.x.toFloat(),
                windowSize.y.toFloat(),
                (coo.x - 20).toFloat(),
                (windowSize.y - 40).toFloat(),
                paint
            )
            drawLine(
                coo.x.toFloat(),
                windowSize.y.toFloat(),
                (coo.x + 20).toFloat(),
                (windowSize.y - 40).toFloat(),
                paint
            )
        }

        drawText4Coo(recording, coo, windowSize, paint)
        return picture

    }

    fun drawText4Coo(canvas: Canvas, coo: Point, windowSize: Point, paint: Paint) {
        paint.textSize = 50f
        canvas.drawText("x", (windowSize.x - 60).toFloat(), (coo.y - 40).toFloat(), paint)
        canvas.drawText("y", (coo.x - 40).toFloat(), (windowSize.y - 60).toFloat(), paint)
        paint.textSize = 25f

        for (i in 1 until (windowSize.x - coo.x) / 50) {
            paint.strokeWidth = 2f
            canvas.drawText((100 * i).toString(), (coo.x - 20 + 100 * i).toFloat(), (coo.y + 40).toFloat(), paint)
            paint.strokeWidth = 5f
            canvas.drawLine(
                (coo.x + 100 * i).toFloat(),
                coo.y.toFloat(),
                (coo.x + 100 * i).toFloat(),
                (coo.y - 10).toFloat(),
                paint
            )
        }

        //X负轴文字
        for (i in 1 until coo.x / 50) {
            paint.strokeWidth = 2f
            canvas.drawText((-100 * i).toString(), (coo.x - 20 - 100 * i).toFloat(), (coo.y + 40).toFloat(), paint)
            paint.strokeWidth = 5f
            canvas.drawLine(
                (coo.x - 100 * i).toFloat(),
                coo.y.toFloat(),
                (coo.x - 100 * i).toFloat(),
                (coo.y - 10).toFloat(),
                paint
            )
        }

        //y正轴文字
        for (i in 1 until (windowSize.y - coo.y) / 50) {
            paint.strokeWidth = 2f
            canvas.drawText((100 * i).toString(), (coo.x + 20).toFloat(), (coo.y + 10 + 100 * i).toFloat(), paint)
            paint.strokeWidth = 5f
            canvas.drawLine(
                coo.x.toFloat(),
                (coo.y + 100 * i).toFloat(),
                (coo.x + 10).toFloat(),
                (coo.y + 100 * i).toFloat(),
                paint
            )
        }

        //y负轴文字
        for (i in 1 until coo.y / 50) {
            paint.strokeWidth = 2f
            canvas.drawText((-100 * i).toString(), (coo.x + 20).toFloat(), (coo.y + 10 - 100 * i).toFloat(), paint)
            paint.strokeWidth = 5f
            canvas.drawLine(
                coo.x.toFloat(),
                (coo.y - 100 * i).toFloat(),
                (coo.x + 10).toFloat(),
                (coo.y - 100 * i).toFloat(),
                paint
            )
        }


    }


}