package com.android.nilagut.practicareversi.utils;


import android.os.CountDownTimer;

class CountDown extends CountDownTimer {
    private final GameBoard gameBoard;
    private long timeToFinish;

    CountDown(long millisInFuture, long countDownInterval, GameBoard gameBoard) {
        super(millisInFuture, countDownInterval);
        this.gameBoard = gameBoard;
    }

    @Override
    public void onFinish() {
        gameBoard.timeEnd = true;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        this.timeToFinish = millisUntilFinished;
    }

    public long getTime() {
        return timeToFinish;
    }
}
