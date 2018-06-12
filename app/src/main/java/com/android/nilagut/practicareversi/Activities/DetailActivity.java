package com.android.nilagut.practicareversi.Activities;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.nilagut.practicareversi.Fragments.DetailFragment;
import com.android.nilagut.practicareversi.R;

public class DetailActivity extends FragmentActivity {

    public static final String CellSelected = "positionSelected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailFragment detalle = (DetailFragment) getSupportFragmentManager().
                findFragmentById(R.id.FrgDetalle);
        detalle.viewDetails(getIntent().getIntExtra(CellSelected, 0));
    }
}
