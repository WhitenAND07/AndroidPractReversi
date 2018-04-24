package com.android.nilagut.practicareversi;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toolbar;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Button botoinici = (Button) findViewById(R.id.botoinici);
        botoinici.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId() == R.id.botoinici){
            finish();
        }
    }
}

