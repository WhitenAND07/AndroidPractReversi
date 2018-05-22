package com.android.nilagut.practicareversi.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.nilagut.practicareversi.Activities.ConfigurationActivity;
import com.android.nilagut.practicareversi.Activities.HelpActivity;
import com.android.nilagut.practicareversi.R;

public class InitialActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        Button btnHelp = (Button) findViewById(R.id.btnHelp);
        Button btnInit = (Button) findViewById(R.id.btnInit);
        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnHelp.setOnClickListener(this);
        btnInit.setOnClickListener(this);
        btnExit.setOnClickListener(this);
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
            case R.id.btnExit:
                finish();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.config_option:
                Intent intent1 = new Intent(this, ConfigurationActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}



