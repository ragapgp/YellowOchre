package com.example.yellowochre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    Button bQuiz;
    String sSavedUsername, sSavedPassword;
    public static final String prefName = "userpref";
    SessionManager sessionManager;
    int chkScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sessionManager = new SessionManager(getApplicationContext());
        SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        final SharedPreferences.Editor edi = sph.edit();

        sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        final String chkusr = sph.getString("key_username", null);
        final int chks = sph.getInt("totalScore", 0);
        sessionManager = new SessionManager(getApplicationContext());
        int updatedscore = sessionManager.getScore();
        String updateduser = sessionManager.getUsername();

        TextView wWelcomeuser = (TextView) findViewById((R.id.welcomeText));
        TextView pPlayHistory = (TextView) findViewById(R.id.playhistory);
        TextView sShowScore = (TextView) findViewById(R.id.showscore);
        bQuiz = (Button) findViewById(R.id.button_quiz);

        wWelcomeuser.setText("Welcome " + sessionManager.getUsername());
        sessionManager.setScore(updatedscore);
        sessionManager.setUsername(updateduser);
        sShowScore.setText("Score is " + sessionManager.getScore());
        pPlayHistory.setText("Last played :" + sessionManager.getTime());
        //wWelcomeuser.setText("Welcome " + updateduser);

        bQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Score in welcomeactivity  is " + chks);
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
                edi.clear();
                edi.commit();
            }
        });
    }
}