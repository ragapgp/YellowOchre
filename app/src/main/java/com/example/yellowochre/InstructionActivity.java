package com.example.yellowochre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InstructionActivity extends AppCompatActivity {

    Button bQuiz;
    String sSavedUsername, sSavedPassword;
    SharedPreferences shp;
    public static final String prefName = "userpref";
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        sessionManager = new SessionManager(getApplicationContext());
        TextView wWelcomeuser = (TextView) findViewById((R.id.editText_username));

        //wWelcomeuser.setText("Welcome" + sessionManager.getUsername());

        shp = getApplicationContext().getSharedPreferences(prefName, 0);
        sSavedUsername = shp.getString(sSavedUsername, null);

        /*if (sSavedUsername != null){
            wWelcomeuser.setText("Welcome " + sSavedUsername);
        }*/

        TextView mInstrHeading = (TextView)findViewById(R.id.instruction_heading);
        TextView mInstrPara = (TextView)findViewById(R.id.instruction_para);

        StringBuilder sb =  new StringBuilder();
        //String head_message = "Instructions to play the Quiz";
        String para_message = "1. This quiz has 5 questions.\n2. Quiz questions are in multiple choice,multiple answers format." +
                "\n3. If the chosen answer is correct, next question will be displayed." +
                "\n4. After all the questions are answered, results will be displayed.";

        //mInstrHeading.setText(head_message);
        mInstrPara.setText(para_message);

        //Declare fields
        bQuiz = (Button) findViewById(R.id.button_quiz);

        //quiz button functionality
        bQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.setLogin(false);
                Intent intent = new Intent(InstructionActivity.this, QuizActivity.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),
                // "Login is Successful...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
