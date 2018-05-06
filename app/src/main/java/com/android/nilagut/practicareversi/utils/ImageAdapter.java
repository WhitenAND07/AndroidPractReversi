package com.android.nilagut.practicareversi.utils;


import android.widget.BaseAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nilagut.practicareversi.Activities.ResultActivity;
import com.android.nilagut.practicareversi.R;

public class ImageAdapter extends BaseAdapter{

    private Activity activitat;
    private Gamelogic tauler;
    private TextView caselles, puntuacio1, puntuacio2;
    private TextView temps;
    private boolean ambTemps;
    private int MIDA;
    private String nom;

    public ImageAdapter(Activity a, Gamelogic tauler, String nom, int mida,
                        boolean ambTemps, TextView caselles, TextView temps, TextView puntuacio1,
                        TextView puntuacio2){
        activitat = a;
        this.tauler = tauler;
        this.nom = nom;
        this.ambTemps = ambTemps;
        this.caselles = caselles;
        this.temps = temps;
        this.puntuacio1 = puntuacio1;
        this.puntuacio2 = puntuacio2;
        actualitzarMarcadors();
        actualitzarTemps();

    }

    private void actualitzarMarcadors() {
        this.caselles.setText(String.valueOf(MIDA * MIDA - tauler.getCasellesusuari().size() -
            tauler.getCasellespc().size()));
        this.puntuacio1.setText(String.valueOf(tauler.getCasellesusuari().size()));
        this.puntuacio2.setText(String.valueOf(tauler.getCasellespc().size()));
    }

    private void actualitzarTemps() {
        if(ambTemps){
            temps.setText(String.valueOf(tauler.obtenirTemps() / opcions.SEGON));
        }
        else{
            temps.setText(String.valueOf((System.currentTimeMillis() / opcions.SEGON) - tauler.temps));
        }
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int posicio, View convertView, ViewGroup parent){
        Button boto;
        if (convertView == null) {
            boto = new Button(activitat);
            if (MIDA*MIDA == 64) {
                boto.setLayoutParams(new GridView.LayoutParams(45, 45));
                boto.setPadding(5, 5, 5, 5);
            } else if (MIDA*MIDA == 36) {
                boto.setLayoutParams(new GridView.LayoutParams(60, 60));
                boto.setPadding(5, 5, 5, 5);
            } else {
                boto.setLayoutParams(new GridView.LayoutParams(100, 100));
                boto.setPadding(5, 5, 5, 5);
            }

        } else {
            boto = (Button) convertView;
        }

        boto.setBackgroundResource(afegirFitxa(posicio));
        boto.setOnClickListener(new MyOnClickListener(posicio, activitat));
        boto.setId(posicio);
        return boto;
    }

    private int afegirFitxa(int posicio) {
        if(tauler.getCasellesusuari().contains(posicio)){
            return R.drawable.redpiece;
        }
        else if(tauler.getCasellespc().contains(posicio)){
            return R.drawable.blackpiece;
        }
        else if(tauler.possiblesPosicionsCaselles().contains(posicio)){
            return R.drawable.blackpiece;
        }
        else{
            return R.drawable.blackpiece;
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        private final int posicio;
        private Context context;

        MyOnClickListener(int posicio, Context context){
            this.posicio = posicio;
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            if (tauler.possiblesPosicionsCaselles().contains(posicio)) {
                tirar(posicio);
                if (acabat()) createNewActivity();
            } else {
                Toast.makeText(context, "Moviment invàlid. torna-ho a provar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createNewActivity() {
        int tempsRestant;
        if (ambTemps){
            tempsRestant = tauler.obtenirTemps() / opcions.SEGON;
        }
        else{
            tempsRestant = (int) (System.currentTimeMillis() / opcions.SEGON - tauler.temps);
        }

        Intent intent = new Intent(activitat, ResultActivity.class);
        intent.putExtra(opcions.ALIAS,nom);
        intent.putExtra(opcions.TEMPS, ambTemps);
        intent.putExtra(opcions.TEMPSRESTANT, tempsRestant);
        intent.putExtra(opcions.PUNTUACIO1, Integer.parseInt(puntuacio1.toString()));
        intent.putExtra(opcions.PUNTUACIO2, Integer.parseInt(puntuacio2.toString()));
        intent.putExtra(opcions.MIDA, MIDA);
        activitat.startActivity(intent);
        activitat.finish();
    }

    private boolean acabat() {
        if (tauler.finalitzat()) {
            return true;
        } else {
            if (tauler.tempsacabat) {
                return true;
            } else if (tauler.possiblesPosicionsCaselles().size() == 0) {
                tauler.canviTorn();
                tauler.possiblesPosicions();
                notifyDataSetChanged();
                return tauler.possiblesPosicionsCaselles().size() == 0;
            } else {
                return false;
            }
        }
    }

    private void tirar(int posicio) {
        tauler.omplirCaselles(posicio);
        tauler.canviTorn();
        tauler.possiblesPosicions();
        actualitzarMarcadors();
        actualitzarTemps();
        notifyDataSetChanged();
    }
}






