package com.mark.testktcubic.view

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.mark.testktcubic.HelpDraw
import com.mark.testktcubic.L
import com.mark.testktcubic.Utils


class Lever1CubicView : View {

    private val mCoo = Point(500, 500)//坐标系
    private lateinit var mCooPicture: Picture//坐标系canvas元件
    private lateinit var mGridPicture: Picture//网格canvas元件
    private lateinit var mHelperPaint: Paint//辅助画笔
    private val c1p0 = Point(0, 0)
    private val c1p1 = Point(300, 0)
    private val c1p2 = Point(150, -200)
    private val c1p3 = Point(300, -200)

    private val src = Point(0, 0)

    private var mPaint: Paint
    private var mPath: Path
    private var mAnimator: ValueAnimator


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {
        //初始化画笔
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color = Color.BLUE
        mPaint.strokeWidth = 5F
        //初始化主路径
        mPath = Path()

        //初始化辅助
        mHelperPaint = HelpDraw.getHelpPaint(0xffF83517.toInt())
        mCooPicture = HelpDraw.getCoo(context, mCoo)
        mGridPicture = HelpDraw.getGrid(context)

        mAnimator = ValueAnimator.ofFloat(1F, 0F)
        mAnimator.duration = 2000
        mAnimator.repeatMode = ValueAnimator.REVERSE
        mAnimator.repeatCount = -1
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

    private fun helpView(canvas: Canvas) {
        mHelperPaint.strokeWidth = 20f
        HelpDraw.drawPos(canvas, mHelperPaint, c1p0, c1p1, c1p2, c1p3)

        mHelperPaint.strokeWidth = 2f
        HelpDraw.drawLines(canvas, mHelperPaint, c1p0, c1p1, c1p2, c1p3)
    }

    fun setPos(event: MotionEvent, p: Point) {
        p.x = (event.x - mCoo.x).toInt()
        p.y = (event.y - mCoo.y).toInt()
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
//        path.cubicTo(
//            (p3.x * 2 - p2.x).toFloat(),
//            p2.y.toFloat(),
//            (p3.x * 2 - p1.x).toFloat(),
//            p1.y.toFloat(),
//            (p3.x * 2 - p0.x).toFloat(),
//            p0.y.toFloat()
//        )

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

