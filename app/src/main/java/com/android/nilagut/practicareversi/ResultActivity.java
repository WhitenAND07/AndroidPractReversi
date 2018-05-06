package com.android.nilagut.practicareversi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.GridView;

import com.android.nilagut.practicareversi.R;
import com.android.nilagut.practicareversi.Gamelogic;
import com.android.nilagut.practicareversi.ImageAdapter;
import com.android.nilagut.practicareversi.opcions;

import static android.provider.Settings.System.getConfiguration;

public class ResultActivity extends AppCompatActivity {
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);
        }
}
