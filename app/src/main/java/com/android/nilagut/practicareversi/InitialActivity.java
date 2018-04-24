package com.android.nilagut.practicareversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class InitialActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        Button botoajuda = (Button) findViewById(R.id.botoajuda);
        Button botocomensar = (Button) findViewById(R.id.botocomensar);
        Button botosortir = (Button) findViewById(R.id.botosortir);
        botoajuda.setOnClickListener(this);
        botocomensar.setOnClickListener(this);
        botosortir.setOnClickListener(this);

    }


    public void onClick(View v){
        if(v.getId() == R.id.botoajuda){
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.botocomensar){
            Intent intent2 = new Intent(this, ConfigurationActivity.class);
            startActivity(intent2);
        }
        if (v.getId() == R.id.botosortir){
            finish();
        }
    }
}



