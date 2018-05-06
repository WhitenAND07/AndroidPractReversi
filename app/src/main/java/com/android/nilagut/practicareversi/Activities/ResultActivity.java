package com.android.nilagut.practicareversi.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import com.android.nilagut.practicareversi.R;
import com.android.nilagut.practicareversi.utils.Variables;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private int size;
    private boolean withTime;
    private int timeLeft;
    private int score1;
    private int score2;
    private String alias;

    private EditText date;
    private EditText resume;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        date = (EditText) findViewById(R.id.date);
        resume = (EditText) findViewById(R.id.resume);
        email = (EditText) findViewById(R.id.email);
        Button exit = (Button) findViewById(R.id.ResultExitButton);
        exit.setOnClickListener(this);
        Button newGame = (Button) findViewById(R.id.ResultNewButton);
        newGame.setOnClickListener(this);
        Button send = (Button) findViewById(R.id.resultButton);
        send.setOnClickListener(this);
        if (savedInstanceState != null) {
            recuperateInstances(savedInstanceState);
        } else {
            Intent intent = getIntent();
            getIntentValues(intent);
            setEditTexts();
        }
    }

    private void recuperateInstances(Bundle savedInstanceState) {
        date.setText(savedInstanceState.getString(Variables.ResultDate));
        resume.setText(savedInstanceState.getString(Variables.ResultLog));
        email.setText(savedInstanceState.getString(Variables.ResultEmail));
        size = savedInstanceState.getInt(Variables.SIZE, 0);
        withTime = savedInstanceState.getBoolean(Variables.TIME);
        timeLeft = savedInstanceState.getInt(Variables.TIME_LEFT, 0);
        score1 = savedInstanceState.getInt(Variables.PLAYER1_SCORE, 0);
        score2 = savedInstanceState.getInt(Variables.PLAYER2_SCORE, 0);
        alias = savedInstanceState.getString(Variables.USER);
    }

    private void setEditTexts() {
        Date actualDate = new Date();
        date.setText(actualDate.toString());
        createLog();
    }

    private void createLog() {
        String moreLog = "";
        if (withTime) {
            moreLog += "\n" + Variables.HaveLeft + timeLeft + Variables.NANOSEGONS;
        }
        if (timeLeft == 0) {
            resume.setText(getString(R.string.Alias) + alias + ". " +
                    getString(R.string.SizeOfTheGrid) + String.valueOf(size) + ".\n" +
                    getString(R.string.Time) + getString(R.string.You) + String.valueOf(score1) +
                    " " + getString(R.string.Player2) + String.valueOf(score2) + "." +
                    Math.abs(score1 - score2) + getString(R.string.Difference) + "." +
                    Math.abs((score1 + score2) - size * size) + " " + getString(R.string.Left) + moreLog);
            createToast(R.string.Time, R.drawable.timer);
        } else if (score1 > score2) {
            resume.setText(getString(R.string.Alias) + alias + ". " +
                    getString(R.string.SizeOfTheGrid) + String.valueOf(size) + ".\n" +
                    getString(R.string.Win) + getString(R.string.You) + String.valueOf(score1) +
                    " " + getString(R.string.Player2) + String.valueOf(score2) + "." +
                    Math.abs(score1 - score2) + getString(R.string.Difference) + "." +
                    Math.abs((score1 + score2) - size * size) + " " + getString(R.string.Left) + moreLog);
            createToast(R.string.Win, R.drawable.copa);
        } else if (score2 > score1) {
            resume.setText(getString(R.string.Alias) + alias + ". " +
                    getString(R.string.SizeOfTheGrid) + String.valueOf(size) + ".\n" +
                    getString(R.string.Lose) + getString(R.string.You) + String.valueOf(score1) +
                    " " + getString(R.string.Player2) + String.valueOf(score2) + "." +
                    Math.abs(score1 - score2) + getString(R.string.Difference) + "." +
                    Math.abs((score1 + score2) - size * size) + " " + getString(R.string.Left) + moreLog);
            createToast(R.string.Lose, R.drawable.caca);
        } else if (score1 == score2) {
            resume.setText(getString(R.string.Alias) + alias + ". " +
                    getString(R.string.SizeOfTheGrid) + String.valueOf(size) + ".\n" +
                    getString(R.string.Draw) + getString(R.string.You) + String.valueOf(score1) +
                    " " + getString(R.string.Player2) + String.valueOf(score2) + "." +
                    Math.abs(score1 - score2) + getString(R.string.Difference) + "." +
                    Math.abs((score1 + score2) - size * size) + " " + getString(R.string.Left) + moreLog);
            createToast(R.string.Draw, R.drawable.empate);
        } else if (score1 == score2 && timeLeft > 0) {
            resume.setText(getString(R.string.Alias) + alias + ". " +
                    getString(R.string.SizeOfTheGrid) + String.valueOf(size) + ".\n" +
                    getString(R.string.Encallat) + getString(R.string.You) + String.valueOf(score1) +
                    " " + getString(R.string.Player2) + String.valueOf(score2) + "." +
                    Math.abs(score1 - score2) + getString(R.string.Difference) + "." +
                    Math.abs((score1 + score2) - size * size) + " " + getString(R.string.Left) + moreLog);
            createToast(R.string.Encallat, R.drawable.stop);
        }
    }

    private void getIntentValues(Intent intent) {
        size = intent.getIntExtra(Variables.SIZE, 0);
        withTime = intent.getBooleanExtra(Variables.TIME, false);
        timeLeft = intent.getIntExtra(Variables.TIME_LEFT, 20);
        score1 = intent.getIntExtra(Variables.PLAYER1_SCORE, 0);
        score2 = intent.getIntExtra(Variables.PLAYER2_SCORE, 0);
        alias = intent.getStringExtra(Variables.USER);
    }

    private void createToast(int resourceText, int resourceImage) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.ToastImage);
        image.setImageResource(resourceImage);
        TextView text = (TextView) layout.findViewById(R.id.ToastText);
        text.setText(resourceText);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ResultExitButton:
                finish();
                break;
            case R.id.ResultNewButton:
                Intent intent = new Intent(this, ConfigurationActivity.class);
                finish();
                startActivity(intent);
                break;
            case R.id.resultButton:
                if (!email.getText().toString().isEmpty()) {
                    Intent intent1 = new Intent(Intent.ACTION_SENDTO,
                            Uri.parse("mailto:" + email.getText().toString()));
                    intent1.putExtra(Intent.EXTRA_SUBJECT, R.string.subject);
                    intent1.putExtra(Intent.EXTRA_TEXT, resume.getText().toString());
                    startActivity(intent1);
                } else {
                    Toast.makeText(this, "The field email it's empty", Toast.LENGTH_SHORT).show();
                }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Variables.ResultDate, date.getText().toString());
        outState.putString(Variables.ResultLog, resume.getText().toString());
        outState.putString(Variables.ResultEmail, email.getText().toString());
        outState.putInt(Variables.SIZE, size);
        outState.putBoolean(Variables.TIME, withTime);
        outState.putInt(Variables.TIME_LEFT, timeLeft);
        outState.putInt(Variables.PLAYER1_SCORE, score1);
        outState.putInt(Variables.PLAYER2_SCORE, score2);
        outState.putString(Variables.USER, alias);
    }
}
