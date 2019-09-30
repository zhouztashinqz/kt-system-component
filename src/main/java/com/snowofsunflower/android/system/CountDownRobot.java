package com.snowofsunflower.android.system;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * handler count down.
 * Created by Zhouztashin on 2015/7/15.
 */
public abstract class CountDownRobot {


    private final int STATUS_COUNT_DOWN = 1;
    private final int STATUS_STOP = 2;
    private final int DEFALUT_COUNT_DOWN_TIME = 60;
    private Handler mHandler;
    /**
     * default is 60
     */
    private int mTime;
    private int mStatus;
    private int mCurrentTime;
    private Timer mTimer;
    private TimerTask mTimerTask;


    public CountDownRobot(Handler handler) {
        this.mHandler = handler;
        mTime = DEFALUT_COUNT_DOWN_TIME;
        mCurrentTime = mTime;
        mStatus = STATUS_STOP;
    }

    public CountDownRobot() {
        mTime = DEFALUT_COUNT_DOWN_TIME;
        mCurrentTime = mTime;
        mStatus = STATUS_STOP;
    }

    public int getCurrentTime() {
        return mCurrentTime;
    }

    /**
     * you need to invoke this method when Count down already stopped,or not calling start yet.
     *
     * @param time
     */
    public void setCountDownTime(int time) {

        if (mStatus != STATUS_STOP) {
            return;
        }
        if (time < 0) {
            mTime = 0;
            return;
        }
        mTime = time;
        mCurrentTime = mTime;
    }

    /**
     * start count down.
     */
    public synchronized void start() {
        //execute task every 1 second.
        if (mStatus == STATUS_COUNT_DOWN) {
            return;
        }
        mStatus = STATUS_COUNT_DOWN;
        //remove all timetask.

        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                countdown();
            }
        };
        if (mTimer != null) {
            mTimer.cancel();
        }
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(mTimerTask, 50, 1000);
    }

    /**
     * stop count down.
     */
    public synchronized void stop() {
        mCurrentTime = mTime;
        mStatus = STATUS_STOP;
        if (mTimer != null) {
            if (mTimerTask != null) {
                mTimerTask.cancel();
            }
            mTimer.cancel();
        }
    }

    private void countdown() {
        mCurrentTime = mCurrentTime - 1;
        if (mCurrentTime < 0) {
            stop();
            if (mHandler != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onCountDownEnd();
                    }
                });
            } else {
                onCountDownEnd();
            }

        } else {
            if (mHandler != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onCountDown(mCurrentTime);
                    }
                });
            } else {
                onCountDown(mCurrentTime);
            }

        }
    }

    public abstract void onCountDown(int currentTime);

    public abstract void onCountDownEnd();

}
