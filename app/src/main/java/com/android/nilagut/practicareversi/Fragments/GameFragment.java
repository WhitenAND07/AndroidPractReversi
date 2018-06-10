package com.android.nilagut.practicareversi.Fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.android.nilagut.practicareversi.R;
import com.android.nilagut.practicareversi.utils.GameBoard;
import com.android.nilagut.practicareversi.utils.ImageAdapter;
import com.android.nilagut.practicareversi.utils.Variables;


public class GameFragment extends Fragment {

    private String user;
    private boolean time;
    private int countDown = 40;
    private int size;
    private TextView cells, score1, score2, timing;
    private GameBoard boardGame;
    public GameListener listener;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPreferences();
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
        else {
            boardGame = new GameBoard(size);
            boardGame.initGameBoard(time, countDown);
        }
        startGridView();
    }
    private void getPreferences(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        user = prefs.getString(getResources().getString(R.string.user_key),
                getResources().getString(R.string.user_default));
        time = prefs.getBoolean(getResources().getString(R.string.time_key), true);
        countDown = Integer.valueOf(prefs.getString(getResources().getString(R.string.selectTime_key),
                "60"));
        size = Integer.valueOf(prefs.getString(getResources().getString(R.string.board_key),
                getResources().getString(R.string.board_Default_key)));

        cells = (TextView) getView().findViewById(R.id.cells);
        timing = (TextView) getView().findViewById(R.id.timing);
        score1 = (TextView) getView().findViewById(R.id.score1);
        score2 = (TextView) getView().findViewById(R.id.score2);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        this.user = savedInstanceState.getString(getResources().getString(R.string.user_key));
        this.time = savedInstanceState.getBoolean(getResources().getString(R.string.time_key));
        this.countDown = savedInstanceState.getInt(getResources().getString(R.string.selectTime_key));
        this.size = savedInstanceState.getInt(getResources().getString(R.string.board_key));
        this.boardGame = savedInstanceState.getParcelable(Variables.GAMEBOARD);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Variables.GAMEBOARD, boardGame);
        outState.putString(getResources().getString(R.string.user_key), user);
        outState.putInt(getResources().getString(R.string.board_key), size);
        outState.putBoolean(getResources().getString(R.string.time_key), time);
        outState.putInt(getResources().getString(R.string.selectTime_key), countDown);
    }
    private void startGridView (){
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), boardGame, user, size, time,
                cells, timing, score1, score2, listener);
        GridView board = (GridView) getView().findViewById(R.id.board);
        board.setAdapter(imageAdapter);
        board.setBackgroundColor(getResources().getColor(R.color.green));
        board.setNumColumns(size);
    }
    public interface GameListener {
        void onGameItemSelected(Integer position, GameBoard gameBoard);
    }

    public void onAttach(Activity c){
        super.onAttach(c);
        try {
            listener = (GameListener) c;
        }

        catch (ClassCastException e){
            throw new ClassCastException(c.toString()+"No listener");
        }
    }

    public void setGameListener(GameListener listener){
       this.listener = listener;
    }
}
