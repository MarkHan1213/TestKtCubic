package com.mark.testktcubic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import com.mark.testktcubic.HelpDraw
import com.mark.testktcubic.L

class Lever3CubicView : BaseCubicView {


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
        mPath.lineTo(0f, 0f)


        mPath.cubicTo(//第一段
            r * CIRCLE_ARRAY[0][0], r * CIRCLE_ARRAY[0][1],
            r * CIRCLE_ARRAY[1][0], r * CIRCLE_ARRAY[1][1],
            r * CIRCLE_ARRAY[2][0], r * CIRCLE_ARRAY[2][1]
        )

        mPath.cubicTo(//第二段
            r * CIRCLE_ARRAY[3][0], r * CIRCLE_ARRAY[3][1],
            r * CIRCLE_ARRAY[4][0], r * CIRCLE_ARRAY[4][1],
            r * CIRCLE_ARRAY[5][0] + src.x - 2 * r, r * CIRCLE_ARRAY[5][1] + src.y
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
//        helpView(canvas);
        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                mAnimator.start()
                src.x = (event.x - mCoo.x).toInt()
                src.y = (event.y - mCoo.y).toInt()

                L.d(src.toString() + L.l())
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

    override fun helpView(canvas: Canvas) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}