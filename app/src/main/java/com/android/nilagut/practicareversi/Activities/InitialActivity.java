package com.android.nilagut.practicareversi.Activities;

import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.nilagut.practicareversi.Activities.ConfigurationActivity;
import com.android.nilagut.practicareversi.Activities.HelpActivity;
import com.android.nilagut.practicareversi.Fragments.ConsultFragment;
import com.android.nilagut.practicareversi.Fragments.PreferencesFragment;
import com.android.nilagut.practicareversi.R;

public class InitialActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        appbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(appbar);

        Button btnHelp = (Button) findViewById(R.id.btnHelp);
        Button btnInit = (Button) findViewById(R.id.btnInit);
        Button btnConsult = (Button) findViewById(R.id.btnConsult);
        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnHelp.setOnClickListener(this);
        btnInit.setOnClickListener(this);
        btnConsult.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHelp:
                Intent intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.btnInit:
                Intent intent1 = new Intent(this, GameActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.btnConsult:
                Intent intent2 = new Intent(this, ConsultFragment.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.btnExit:
                finish();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.config_option:
                Intent intent1 = new Intent(this, PreferencesFragment.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}



