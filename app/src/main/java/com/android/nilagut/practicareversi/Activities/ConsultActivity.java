package com.android.nilagut.practicareversi.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.nilagut.practicareversi.Activities.ConfigurationActivity;
import com.android.nilagut.practicareversi.Activities.HelpActivity;
import com.android.nilagut.practicareversi.R;

public class ConsultActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        appbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(appbar);
    }

    @Override
    public void onClick(View v) {

    }
}