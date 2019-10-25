package com.snowofsunflower.android.system

import android.os.Handler
import java.util.*

/**
 * handler count down.
 * Created by Zhouztashin on 2015/7/15.
 */
abstract class CountDownRobot {


    private val STATUS_COUNT_DOWN = 1
    private val STATUS_STOP = 2
    private val DEFALUT_COUNT_DOWN_TIME = 60
    private var mHandler: Handler? = null
    /**
     * default is 60
     */
    private var mTime: Int = 0
    private var mStatus: Int = 0
    var currentTime: Int = 0
        private set
    private var mTimer: Timer? = null
    private var mTimerTask: TimerTask? = null


    constructor(handler: Handler) {
        this.mHandler = handler
        mTime = DEFALUT_COUNT_DOWN_TIME
        currentTime = mTime
        mStatus = STATUS_STOP
    }

    constructor() {
        mTime = DEFALUT_COUNT_DOWN_TIME
        currentTime = mTime
        mStatus = STATUS_STOP
    }

    /**
     * you need to invoke this method when Count down already stopped,or not calling start yet.
     *
     * @param time
     */
    fun setCountDownTime(time: Int) {

        if (mStatus != STATUS_STOP) {
            return
        }
        if (time < 0) {
            mTime = 0
            return
        }
        mTime = time
        currentTime = mTime
    }

    /**
     * start count down.
     */
    @Synchronized
    fun start() {
        //execute task every 1 second.
        if (mStatus == STATUS_COUNT_DOWN) {
            return
        }
        mStatus = STATUS_COUNT_DOWN
        //remove all timetask.

        mTimerTask = object : TimerTask() {
            override fun run() {
                countdown()
            }
        }
        if (mTimer != null) {
            mTimer!!.cancel()
        }
        mTimer = Timer()
        mTimer!!.scheduleAtFixedRate(mTimerTask, 50, 1000)
    }

    /**
     * stop count down.
     */
    @Synchronized
    fun stop() {
        currentTime = mTime
        mStatus = STATUS_STOP
        if (mTimer != null) {
            if (mTimerTask != null) {
                mTimerTask!!.cancel()
            }
            mTimer!!.cancel()
        }
    }

    private fun countdown() {
        currentTime -= 1
        if (currentTime < 0) {
            stop()
            mHandler?.post { onCountDownEnd() } ?: onCountDownEnd()

        } else {
            mHandler?.post { onCountDown(currentTime) } ?: onCountDown(currentTime)

        }
    }

    abstract fun onCountDown(currentTime: Int)

    abstract fun onCountDownEnd()

}
