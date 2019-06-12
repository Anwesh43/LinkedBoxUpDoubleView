package com.anwesh.uiprojects.boxupdoubleview

/**
 * Created by anweshmishra on 12/06/19.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 2
val parts : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#4A148C")
val backColor : Int = Color.parseColor("#BDBDBD")
val hFactor : Float = 4f

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap
fun Float.maxValue(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxValue(i, n)) * n

fun Canvas.drawBoxUp(i : Int, sc1 : Float, sc2 : Float, size : Float, paint : Paint) {
    val sf : Float = 1f - 2 * i
    val h : Float = size / hFactor
    val y : Float = (size - h) * sc1.divideScale(i, lines) * sf
    drawLine(0f, 0f, 0f, y, paint)
    for (j in 0..(lines - 1)) {
        save()
        translate(size * (1f - 2 * j) * sc2.divideScale(i, lines), 0f)
        drawLine(0f, 0f, 0f, y, paint)
        restore()
    }
    save()
    translate(0f, y - h * i)
    drawRect(-size / 2, 0f, size / 2, h, paint)
    restore()
}

fun Canvas.drawBUDNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    save()
    translate(w / 2, gap * (i + 1))
    for (j in 0..(parts - 1)) {
        drawBoxUp(j, sc1, sc2, size, paint)
    }
    restore()
}
