package com.example.draggableimageview

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var wContainer = 0
    private var hContainer = 0
    private var timeDoubleClick = 0L

    companion object {
        private const val MARGIN = 10f
        private const val DURATION = 300L
        private const val DOUBLE_CLICK = 200L
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var halfWidth = 0
        ctMain.post {
            wContainer = ctMain.width
            hContainer = ctMain.height
            halfWidth = wContainer / 2
        }

        val listener = View.OnTouchListener(function = { view, motionEvent ->
            Log.d(
                "MMMMMM",
                "${motionEvent.rawX} + ${motionEvent.rawY} : $wContainer x $hContainer : $halfWidth : ${view.width + MARGIN} x ${view.height + MARGIN}"
            )
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                view.y = (motionEvent.rawY - view.height * (1.7)).toFloat()
                view.x = motionEvent.rawX - view.width / 2
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                if (SystemClock.elapsedRealtime() - timeDoubleClick < DOUBLE_CLICK) {
                    cvCircle.visibility = View.INVISIBLE
                }
                timeDoubleClick = SystemClock.elapsedRealtime()

                /*TOP - LEFT*/
                if ((motionEvent.rawX <= (view.width + MARGIN)) && (motionEvent.rawY <= (view.height + MARGIN))) {
                    Log.d("MMMMMM", "TOP - LEFT")
                    val y = MARGIN
                    val x = MARGIN
                    view.animate().x(x).y(y).setDuration(DURATION).start()
                } else if ((motionEvent.rawX <= halfWidth) && (motionEvent.rawY.toInt() <= (view.height + MARGIN))) {
                    Log.d("MMMMMM", "TOP")
                    val y = MARGIN
                    val x = motionEvent.rawX - view.width / 2
                    view.animate().x(x).y(y).setDuration(DURATION).start()
                }
                /*BOTTOM - LEFT*/
                else if ((motionEvent.rawX <= (view.width + MARGIN)) && (motionEvent.rawY >= hContainer - (view.width + MARGIN))) {
                    Log.d("MMMMMM", "BOTTOM - LEFT")
                    val y = hContainer - (view.width + MARGIN)
                    val x = MARGIN
                    view.animate().x(x).y(y).setDuration(DURATION).start()
                } else if ((motionEvent.rawX <= halfWidth) && (motionEvent.rawY.toInt() >= hContainer - (view.width + MARGIN))) {
                    Log.d("MMMMMM", "BOTTOM")
                    val y = hContainer - (view.width + MARGIN)
                    val x = motionEvent.rawX - view.width / 2
                    view.animate().x(x).y(y).setDuration(DURATION).start()
                } else if (motionEvent.rawX <= halfWidth) {
                    Log.d("MMMMMM", "LEFT")
                    val y = (motionEvent.rawY - view.height * (1.7)).toFloat()
                    val x = MARGIN
                    view.animate().x(x).y(y).setDuration(DURATION).start()
                }

                /*TOP - RIGHT*/
                if ((motionEvent.rawX.toInt() > wContainer - (view.width + MARGIN)) && (motionEvent.rawY <= (view.height + MARGIN))) {
                    Log.d("MMMMMM", "TOP - RIGHT")
                    val y = MARGIN
                    val x = wContainer - (view.width + MARGIN)
                    view.animate().x(x).y(y).setDuration(DURATION).start()
                } else if ((motionEvent.rawX.toInt() > halfWidth) && (motionEvent.rawY <= (view.height + MARGIN))) {
                    Log.d("MMMMMM", "TOP")
                    val y = MARGIN
                    val x = motionEvent.rawX - view.width / 2
                    view.animate().x(x).y(y).setDuration(DURATION).start()
                }
                /*BOTTOM - RIGHT*/
                else if ((motionEvent.rawX.toInt() > wContainer - (view.width + MARGIN)) && (motionEvent.rawY >= hContainer - (view.width + MARGIN))) {
                    Log.d("MMMMMM", "BOTTOM - RIGHT")
                    val y = hContainer - (view.width + MARGIN)
                    val x = wContainer - (view.width + MARGIN)
                    view.animate().x(x).y(y).setDuration(DURATION).start()
                } else if ((motionEvent.rawX.toInt() > halfWidth) && (motionEvent.rawY >= hContainer - (view.width + MARGIN))) {
                    Log.d("MMMMMM", "BOTTOM")
                    val y = hContainer - (view.width + MARGIN)
                    val x = motionEvent.rawX - view.width / 2
                    view.animate().x(x).y(y).setDuration(DURATION).start()
                } else if (motionEvent.rawX.toInt() > halfWidth) {
                    Log.d("MMMMMM", "RIGHT")
                    val y = (motionEvent.rawY - view.height * (1.7)).toFloat()
                    val x = wContainer - (view.width + MARGIN)
                    view.animate().x(x).y(y).setDuration(DURATION).start()
                }

            }
            true

        })

        // Declared in our activity_shapes_view.xml file.
        cvCircle.setOnTouchListener(listener)
        ctMain.setOnClickListener {
            if (cvCircle.visibility == View.INVISIBLE) {
                if (SystemClock.elapsedRealtime() - timeDoubleClick < DOUBLE_CLICK) {
                    cvCircle.visibility = View.VISIBLE
                }
                timeDoubleClick = SystemClock.elapsedRealtime()
            }
        }
    }
}