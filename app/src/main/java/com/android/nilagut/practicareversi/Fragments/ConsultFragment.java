package com.android.nilagut.practicareversi.Fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.android.nilagut.practicareversi.Activities.DetailActivity;
import com.android.nilagut.practicareversi.Activities.InitialActivity;
import com.android.nilagut.practicareversi.R;

public class ConsultFragment extends FragmentActivity implements ListFragment.GameListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        ListFragment fragmentListado = (ListFragment) getSupportFragmentManager().
                findFragmentById(R.id.ListFrag);
        fragmentListado.setGameListener(this);
    }
    public void onClick(View v) {
        Intent intent = new Intent(this, InitialActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onGameSelected(int position) {
        DetailFragment fgdet = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        boolean hayDetalle = (fgdet != null && fgdet.isInLayout());
        if (hayDetalle) {
            fgdet.viewDetails(position);
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.POSITION, position);
            startActivity(intent);
        }
    }
}
