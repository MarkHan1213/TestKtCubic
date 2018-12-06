package com.mark.testktcubic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import com.mark.testktcubic.HelpDraw

class Lever2CubicView : BaseCubicView {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        mAnimator.addUpdateListener {
            runNum = it.animatedValue as Float
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

        mPaint.style = Paint.Style.FILL
        mPath.lineTo(0F, 0F)

        mPath.cubicTo(//第一段
            r * CIRCLE_ARRAY[0][0], r * CIRCLE_ARRAY[0][1],
            r * CIRCLE_ARRAY[1][0] - (1 - runNum) * 4f * r, r * CIRCLE_ARRAY[1][1],
            r * CIRCLE_ARRAY[2][0], r * CIRCLE_ARRAY[2][1]
        )

        mPath.cubicTo(//第二段
            r * CIRCLE_ARRAY[3][0] + (1 - runNum) * 4f * r, r * CIRCLE_ARRAY[3][1],
            r * CIRCLE_ARRAY[4][0], r * CIRCLE_ARRAY[4][1],
            r * CIRCLE_ARRAY[5][0], r * CIRCLE_ARRAY[5][1]
        )

        mPath.cubicTo(//第三段
            r * CIRCLE_ARRAY[6][0], r * CIRCLE_ARRAY[6][1],
            r * CIRCLE_ARRAY[7][0], r * CIRCLE_ARRAY[7][1],
            r * CIRCLE_ARRAY[8][0], r * CIRCLE_ARRAY[8][1]
        )

        mPath.cubicTo(//第四段
            r * CIRCLE_ARRAY[9][0], r * CIRCLE_ARRAY[9][1],
            r * CIRCLE_ARRAY[10][0], r * CIRCLE_ARRAY[10][1],
            r * CIRCLE_ARRAY[11][0], r * CIRCLE_ARRAY[11][1]
        )
        canvas.drawPath(mPath, mPaint)
        helpView(canvas)
        canvas.restore()

    }

    override fun helpView(canvas: Canvas) {
        mHelperPaint.strokeWidth = 20f


        val pointF0 = PointF(0f, 0f)
        val pointF1 = PointF(r * CIRCLE_ARRAY[0][0], r * CIRCLE_ARRAY[0][1])
        val pointF2 = PointF(r * CIRCLE_ARRAY[1][0] - (1 - runNum) * 4f * r, r * CIRCLE_ARRAY[1][1])
        val pointF3 = PointF(r * CIRCLE_ARRAY[2][0], r * CIRCLE_ARRAY[2][1])

        HelpDraw.drawPos(canvas, mHelperPaint, pointF0, pointF1, pointF2, pointF3)

        val pointF4 = PointF(r * CIRCLE_ARRAY[3][0] + (1 - runNum) * 4f * r, r * CIRCLE_ARRAY[3][1])
        val pointF5 = PointF(r * CIRCLE_ARRAY[4][0], r * CIRCLE_ARRAY[4][1])
        val pointF6 = PointF(r * CIRCLE_ARRAY[5][0], r * CIRCLE_ARRAY[5][1])
        HelpDraw.drawPos(canvas, mHelperPaint, pointF4, pointF5, pointF6)


        val pointF7 = PointF(r * CIRCLE_ARRAY[6][0], r * CIRCLE_ARRAY[6][1])
        val pointF8 = PointF(r * CIRCLE_ARRAY[7][0], r * CIRCLE_ARRAY[7][1])
        val pointF9 = PointF(r * CIRCLE_ARRAY[8][0], r * CIRCLE_ARRAY[8][1])
        HelpDraw.drawPos(canvas, mHelperPaint, pointF7, pointF8, pointF9)

        val pointF10 = PointF(r * CIRCLE_ARRAY[9][0], r * CIRCLE_ARRAY[9][1])
        val pointF11 = PointF(r * CIRCLE_ARRAY[10][0], r * CIRCLE_ARRAY[10][1])
        val pointF12 = PointF(r * CIRCLE_ARRAY[11][0], r * CIRCLE_ARRAY[11][1])
        HelpDraw.drawPos(canvas, mHelperPaint, pointF10, pointF11, pointF12)


        mHelperPaint.strokeWidth = 2f
        HelpDraw.drawLines(
            canvas, mHelperPaint,
            pointF0, pointF1, pointF2, pointF3,
            pointF3, pointF4, pointF5, pointF6,
            pointF6, pointF7, pointF8, pointF9,
            pointF9, pointF10, pointF11, pointF12
        )

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                mAnimator.start()
                src.x = (event.x - mCoo.x).toInt()
                src.y = (event.y - mCoo.y).toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                mPath.reset()
                src.x = (event.x - mCoo.x).toInt()
                src.y = (event.y - mCoo.y).toInt()
                invalidate()
            }
        }
        return true
    }


}