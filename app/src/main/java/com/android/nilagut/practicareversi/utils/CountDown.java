package com.android.nilagut.practicareversi.utils;


import android.os.CountDownTimer;

public class CountDown extends CountDownTimer {
    private final GameLogic tauler;
    private long timeToFinish;

    CountDown(long millisInFuture, long countDownInterval, GameLogic tauler) {
        super(millisInFuture, countDownInterval);
        this.tauler = tauler;
    }

    @Override
    public void onFinish() {
        tauler.tempsacabat = true;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        this.timeToFinish = millisUntilFinished;
    }

    public long getTime() {
        return timeToFinish;
    }
}
