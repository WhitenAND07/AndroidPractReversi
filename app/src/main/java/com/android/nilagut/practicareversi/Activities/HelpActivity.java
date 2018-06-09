package com.android.nilagut.practicareversi.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.nilagut.practicareversi.R;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Button back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                Intent intent = new Intent(this, InitialActivity.class);
                startActivity(intent);
                finish();
        }
    }
}

