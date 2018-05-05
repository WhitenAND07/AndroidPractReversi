package com.android.nilagut.practicareversi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.GridView;

import com.android.nilagut.practicareversi.R;
import com.android.nilagut.practicareversi.Gamelogic;
import com.android.nilagut.practicareversi.ImageAdapter;
import com.android.nilagut.practicareversi.opcions;

import static android.provider.Settings.System.getConfiguration;

public class GameActivity extends AppCompatActivity {

    private int MIDA;
    private boolean temps;
    private String jugador1;
    private int countDown = 40;

    private TextView caselles, crono, puntuacio1, puntuacio2;

    private Gamelogic tauler;
    private GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        jugador1 = getIntent().getStringExtra(opcions.ALIAS);
        MIDA = getIntent().getIntExtra(opcions.MIDA, 4);
        temps = getIntent().getBooleanExtra(opcions.TEMPS, false);
        caselles = (TextView) findViewById(R.id.casellespendents);
        crono = (TextView) findViewById(R.id.timeon);
        puntuacio1 = (TextView) findViewById(R.id.puntuacio1);
        puntuacio2 = (TextView) findViewById(R.id.puntuacio2);

        if (savedInstanceState == null) començar();
        else getBackState(savedInstanceState);
        iniciarGridView();
    }

    private void iniciarGridView() {
        ImageAdapter imageAdapter = new ImageAdapter(this, tauler, jugador1, MIDA, temps,
                caselles, crono, puntuacio1, puntuacio2);
        this.grid = (GridView) findViewById(R.id.board);
        this.grid.setAdapter(imageAdapter);
        this.grid.setBackgroundColor(getResources().getColor(R.color.green));
        this.grid.setNumColumns(MIDA);
    }

    private void getBackState(Bundle savedInstanceState) {
        tauler = savedInstanceState.getParcelable(opcions.TAULER);
        this.jugador1 = savedInstanceState.getString(opcions.ALIAS);
        this.MIDA = savedInstanceState.getInt(opcions.MIDA);
        this.temps = savedInstanceState.getBoolean(opcions.TEMPS);
    }

    private void començar() {
        tauler = new Gamelogic(MIDA);
        tauler.CrearTauler(temps, countDown);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(opcions.TAULER, tauler);
        outState.putString(opcions.ALIAS, jugador1);
        outState.putInt(opcions.MIDA, MIDA);
        outState.putBoolean(opcions.TEMPS, temps);
    }
}