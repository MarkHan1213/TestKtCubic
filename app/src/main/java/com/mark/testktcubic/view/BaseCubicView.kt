package com.mark.testktcubic.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.mark.testktcubic.HelpDraw

abstract class BaseCubicView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    protected val mCoo = Point(500, 500)
    protected var mCooPicture: Picture
    protected var mGridPicture: Picture
    protected var mHelperPaint: Paint
    protected var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    protected var mPath: Path
    protected var mAnimator: ValueAnimator
    protected val src = Point(0, 0)

    val r = 300f
    val rate = 0.551915024494f
    var runNum = 1f




    /**
     * 贝塞尔曲线控制点
     */
    val CIRCLE_ARRAY = arrayOf(
        //0---第一段线
        floatArrayOf(0f, rate), //控制点1
        floatArrayOf(1 - rate, 1f), //控制点2
        floatArrayOf(1f, 1f), //终点

        //1---第二段线
        floatArrayOf(1 + rate, 1f), //控制点1
        floatArrayOf(2f, rate), //控制点2
        floatArrayOf(2f, 0f), //终点

        //2---第二段线
        floatArrayOf(2f, -rate), //控制点1
        floatArrayOf(1 + rate, -1f), //控制点2
        floatArrayOf(1f, -1f), //终点

        //3---第四段线
        floatArrayOf(1 - rate, -1f), //控制点1
        floatArrayOf(0f, -rate), //控制点2
        floatArrayOf(0f, 0f)//终点
    )

    init {
        //初始化画笔
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
    }

    abstract fun helpView(canvas: Canvas)

    fun setPos(event: MotionEvent, p: Point) {
        p.x = (event.x - mCoo.x).toInt()
        p.y = (event.y - mCoo.y).toInt()
    }
}