package com.android.nilagut.practicareversi.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.GridView;

import com.android.nilagut.practicareversi.R;
import com.android.nilagut.practicareversi.utils.GameBoard;
import com.android.nilagut.practicareversi.utils.ImageAdapter;
import com.android.nilagut.practicareversi.utils.Variables;

public class GameActivity extends AppCompatActivity {

    private int SIZE;
    private boolean time;
    private String player1;
    private int countDown = 40;

    private TextView cells, timing, score1, score2;

    private GameBoard gameBoard;
    private GridView board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getConfiguration();
        if (savedInstanceState == null) initGame();
        else getBackState(savedInstanceState);
        initGridView();
    }

    private void initGridView() {
        ImageAdapter imageAdapter = new ImageAdapter(this, gameBoard, player1, SIZE, time,
                cells, timing, score1, score2);
        this.board = (GridView) findViewById(R.id.board);
        this.board.setAdapter(imageAdapter);
        this.board.setBackgroundColor(getResources().getColor(R.color.green));
        this.board.setNumColumns(SIZE);
    }

    private void getBackState(Bundle savedInstanceState) {
        gameBoard = savedInstanceState.getParcelable(Variables.GAMEBOARD);
        this.player1 = savedInstanceState.getString(Variables.USER);
        this.SIZE = savedInstanceState.getInt(Variables.SIZE);
        this.time = savedInstanceState.getBoolean(Variables.TIME);
    }

    private void initGame() {
        gameBoard = new GameBoard(SIZE);
        gameBoard.initGameBoard(time, countDown);
    }

    private void getConfiguration() {
        player1 = getIntent().getStringExtra(Variables.USER);
        SIZE = getIntent().getIntExtra(Variables.SIZE, 4);
        time = getIntent().getBooleanExtra(Variables.TIME, false);
        cells = (TextView) findViewById(R.id.cells);
        timing = (TextView) findViewById(R.id.timing);
        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Variables.GAMEBOARD, gameBoard);
        outState.putString(Variables.USER, player1);
        outState.putInt(Variables.SIZE, SIZE);
        outState.putBoolean(Variables.TIME, time);
    }
}