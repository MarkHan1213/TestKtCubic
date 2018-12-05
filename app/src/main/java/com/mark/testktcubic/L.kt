package com.mark.testktcubic

import android.util.Log


object L {

    /**
     * 获取当前的类名
     *
     * @return 当前的类名(simpleName)
     */
    private val className: String
        get() {
            var result: String
            val thisMethodStack = Exception().stackTrace[2]
            result = thisMethodStack.className
            val lastIndex = result.lastIndexOf(".")
            result = result.substring(lastIndex + 1, result.length)
            return result
        }

    /**
     * 获取当前的类全名
     *
     * @return 当前的类名（全名）
     */
    val fullClassName: String
        get() {
            val result: String
            val thisMethodStack = Exception().stackTrace[1]
            result = thisMethodStack.className
            return result
        }

    /**
     * 日志输出级别NONE
     */
    val LEVEL_NONE = 0
    /**
     * 日志输出级别E
     */
    val LEVEL_ERROR = 1
    /**
     * 日志输出级别W
     */
    val LEVEL_WARN = 2
    /**
     * 日志输出级别I
     */
    val LEVEL_INFO = 3
    /**
     * 日志输出级别D
     */
    val LEVEL_DEBUG = 4
    /**
     * 日志输出级别V
     */
    val LEVEL_VERBOSE = 5


    /**
     * 是否允许输出log
     */
    private var mDebuggable = LEVEL_VERBOSE

    /**
     * log这个方法就可以显示超链
     *
     * @return 结果
     */
    fun l(): String {
        var result = "   \n:at "
        val thisMethodStack = Exception().stackTrace[1]
        result += thisMethodStack.className + "."
        result += thisMethodStack.methodName
        result += "(" + thisMethodStack.fileName
        result += ":" + thisMethodStack.lineNumber + ")  "
        return result
    }

    /**
     * 获取信息字符串
     *
     * @param o 信息对象
     * @return 信息对象toString
     */
    fun getMsg(o: Any?): String {
        return o?.toString() ?: "null"
    }

    /**
     * 以级别为 d 的形式输出LOG
     */
    fun v(msg: Any) {
        if (mDebuggable >= LEVEL_VERBOSE) {
            Log.v(className, getMsg(msg))
        }
    }

    /**
     * 以级别为 d 的形式输出LOG
     */
    @JvmOverloads
    fun d(msg: Any = "================华丽分割线================") {
        if (mDebuggable >= LEVEL_DEBUG) {
            Log.d(className, getMsg(msg))
        }
    }

    /**
     * 以级别为 i 的形式输出LOG
     */
    fun i(msg: String) {
        if (mDebuggable >= LEVEL_INFO) {
            Log.i(className, getMsg(msg))
        }
    }

    /**
     * 以级别为 w 的形式输出LOG
     */
    fun w(msg: String) {
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(className, getMsg(msg))
        }
    }

    /**
     * 以级别为 w 的形式输出Throwable
     */
    fun w(tr: Throwable) {
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(className, "", tr)
        }
    }

    /**
     * 以级别为 w 的形式输出LOG信息和Throwable
     */
    fun w(msg: String?, tr: Throwable) {
        if (mDebuggable >= LEVEL_WARN && null != msg) {
            Log.w(className, getMsg(msg), tr)
        }
    }

    /**
     * 以级别为 e 的形式输出LOG
     */
    fun e(msg: String) {
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(className, getMsg(msg))
        }
    }

    /**
     * 以级别为 e 的形式输出LOG
     */
    fun e(tag: String, msg: String) {
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(tag, getMsg(msg))
        }
    }

    /**
     * 以级别为 e 的形式输出Throwable
     */
    fun e(tr: Throwable) {
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(className, "", tr)
        }
    }

    /**
     * 以级别为 e 的形式输出LOG信息和Throwable
     */
    fun e(msg: String?, tr: Throwable) {
        if (mDebuggable >= LEVEL_ERROR && null != msg) {
            Log.e(className, getMsg(msg), tr)
        }
    }


}
