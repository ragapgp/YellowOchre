package com.example.yellowochre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    public static final String prefName = "userpref";
    SessionManager sessionManager;
    int totalScore;

    //SharedPreferences sph = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    //SharedPreferences sph = getApplicationContext().getSharedPreferences(prefName, Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        sessionManager = new SessionManager(getApplicationContext());
        SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edi = sph.edit();

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        final TextView totalScoreLabel = (TextView) findViewById(R.id.totalScoreLabel);
        Button returnToQuiz = (Button) findViewById(R.id.returnToQuiz);

        final int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        //final SharedPreferences shp = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        totalScore = sph.getInt("totalscore", 0);
        totalScore += score;

        scoreLabel.setText(score + "/5");
        totalScoreLabel.setText("Total score is " + totalScore);
        System.out.println("Total Score in scoreactivity " + totalScore);

        //final SharedPreferences.Editor edi = sph.edit();
        edi.putInt("totalScore" , totalScore);
        edi.commit();

        returnToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
                SharedPreferences.Editor edi = sph.edit();
                System.out.println("Score in scoreactivity " + score);

                sessionManager.setScore(totalScore);
                System.out.println("Score when qc 4 is : " + totalScore);
                PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().clear().apply();
                //edi.clear();
                //edi.commit();
                //sph.edit().remove("tQuestionNo").commit();
                ////sph.edit().remove("squestion").commit();
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);

                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}