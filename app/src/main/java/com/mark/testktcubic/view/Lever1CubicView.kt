package com.mark.testktcubic.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import com.mark.testktcubic.HelpDraw
import com.mark.testktcubic.L
import com.mark.testktcubic.Utils


class Lever1CubicView : BaseCubicView {

    private val c1p0 = Point(0, 0)
    private val c1p1 = Point(300, 0)
    private val c1p2 = Point(150, -200)
    private val c1p3 = Point(300, -200)


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {
        mAnimator.addUpdateListener {
            val rate = it.animatedValue as Float
            L.d(rate.toString() + L.l())
            c1p2.y = -(rate * 200).toInt()
            c1p3.y = -(rate * 200).toInt()
            mPath.reset()
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        super.onDraw(canvas)
        HelpDraw.draw(canvas, mGridPicture, mCooPicture)
        canvas.save()
        canvas.translate(mCoo.x.toFloat(), mCoo.y.toFloat())

        mPaint.style = Paint.Style.STROKE
        mPath.lineTo(c1p0.x.toFloat(), c1p0.y.toFloat())
        mPath.cubicTo(
            c1p1.x.toFloat(),
            c1p1.y.toFloat(),
            c1p2.x.toFloat(),
            c1p2.y.toFloat(),
            c1p3.x.toFloat(),
            c1p3.y.toFloat()
        )
        reflectY(c1p0, c1p1, c1p2, c1p3, mPath)
        canvas.drawPath(mPath, mPaint)

        helpView(canvas)
        canvas.restore()
    }

    override fun helpView(canvas: Canvas) {
        mHelperPaint.strokeWidth = 20f
        HelpDraw.drawPos(canvas, mHelperPaint, c1p0, c1p1, c1p2, c1p3)

        mHelperPaint.strokeWidth = 2f
        HelpDraw.drawLines(canvas, mHelperPaint, c1p0, c1p1, c1p2, c1p3)

        mHelperPaint.pathEffect = null
        mHelperPaint.style = Paint.Style.FILL
        with(canvas) {
            drawText("起始点p0:" + c1p0.toString(), 700f, -300f, mHelperPaint)
            drawText("控制点p1:" + c1p1.toString(), 700f, -240f, mHelperPaint)
            drawText("控制点p2:" + c1p2.toString(), 700f, -180f, mHelperPaint)
            drawText("终止点p3:" + c1p3.toString(), 700f, -120f, mHelperPaint)

        }
    }


    fun reflectY(p0: Point, p1: Point, p2: Point, p3: Point, path: Path) {
        path.cubicTo(
            (p3.x * 2 - p2.x).toFloat(),
            p2.y.toFloat(),
            (p3.x * 2 - p1.x).toFloat(),
            p1.y.toFloat(),
            (p3.x * 2 - p0.x).toFloat(),
            p0.y.toFloat()
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                mAnimator.start()
                src.x = (event.x - mCoo.x).toInt()
                src.y = (event.y - mCoo.y).toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                if (Utils.judgeCircleArea(src, c1p0, 30F)) {
                    setPos(event, c1p0)
                }
                if (Utils.judgeCircleArea(src, c1p1, 30F)) {
                    setPos(event, c1p1)
                }
                if (Utils.judgeCircleArea(src, c1p2, 30F)) {
                    setPos(event, c1p2)
                }
                if (Utils.judgeCircleArea(src, c1p3, 30F)) {
                    setPos(event, c1p3)
                }
                mPath.reset()
                src.x = (event.x - mCoo.x).toInt()
                src.y = (event.y - mCoo.y).toInt()
                invalidate()
            }
        }
        return true
    }

}

