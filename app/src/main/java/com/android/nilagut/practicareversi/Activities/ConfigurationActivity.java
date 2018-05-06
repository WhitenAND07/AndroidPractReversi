package com.android.nilagut.practicareversi.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.nilagut.practicareversi.R;
import com.android.nilagut.practicareversi.utils.opcions;

public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText alias;
    private RadioButton mida;
    private CheckBox temps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        Button start = (Button) findViewById(R.id.iniciarpartida);
                start.setOnClickListener(this);
                alias = (EditText) findViewById(R.id.aliasEditText1);
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.size);
                mida = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                temps = (CheckBox) findViewById(R.id.timeon);
    }

    @Override
   public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iniciarpartida:
                if (!alias.getText().toString().isEmpty()) {
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra(opcions.ALIAS, alias.getText().toString());
                    intent.putExtra(opcions.MIDA, Integer.parseInt(mida.getText().toString()));
                    intent.putExtra(opcions.TEMPS, temps.isChecked());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Completa tots els camps", Toast.LENGTH_SHORT).show();
                }

                break;
            }
        }
    }



