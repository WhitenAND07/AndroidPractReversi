package com.android.nilagut.practicareversi.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.GridView;

import com.android.nilagut.practicareversi.Fragments.GameFragment;
import com.android.nilagut.practicareversi.R;
import com.android.nilagut.practicareversi.utils.GameBoard;
import com.android.nilagut.practicareversi.utils.ImageAdapter;
import com.android.nilagut.practicareversi.utils.Variables;


public class GameActivity extends FragmentActivity {


    public void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_game);
        GameFragment gameF = (GameFragment) getSupportFragmentManager().findFragmentById
                (R.id.fragmentGame);
    }
}