package com.android.nilagut.practicareversi.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                Intent intent1 = new Intent(this, ConfigurationActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }
}



