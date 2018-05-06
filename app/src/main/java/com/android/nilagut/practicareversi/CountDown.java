package com.android.nilagut.practicareversi;


import android.os.CountDownTimer;

public class CountDown extends CountDownTimer {
    private final Gamelogic tauler;
    private long timeToFinish;

    CountDown(long millisInFuture, long countDownInterval, Gamelogic tauler) {
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
