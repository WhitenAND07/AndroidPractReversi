package com.android.nilagut.practicareversi.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.GridView;

import com.android.nilagut.practicareversi.Fragments.GameFragment;
import com.android.nilagut.practicareversi.Fragments.GameLogsFragment;
import com.android.nilagut.practicareversi.R;
import com.android.nilagut.practicareversi.utils.GameBoard;
import com.android.nilagut.practicareversi.utils.ImageAdapter;
import com.android.nilagut.practicareversi.utils.LogCreator;
import com.android.nilagut.practicareversi.utils.Variables;

import static com.android.nilagut.practicareversi.utils.LogCreator.*;


public class GameActivity extends FragmentActivity implements GameFragment.GameListener {

    private LogCreator logCreator;

    public void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_game);
        GameFragment gameF = (GameFragment) getSupportFragmentManager().findFragmentById
                (R.id.fragmentGame);

        gameF.setGameListener(this);
        logCreator = LogCreator.getINSTANCE(this);
    }

    @Override
    public void onGameItemSelected(Integer position, GameBoard gameBoard) {
        GameLogsFragment gameLogsFragment = (GameLogsFragment) getSupportFragmentManager()
            .findFragmentById(R.id.logFragment);

        if(gameLogsFragment != null && gameLogsFragment.isInLayout()){
            GameLogsFragment gameFragmentDetail = (GameLogsFragment) getSupportFragmentManager()
           .findFragmentById(R.id.logFragment);
            gameFragmentDetail.mostrarLogs(logCreator.logValues(gameBoard, position));
        }
    }
}