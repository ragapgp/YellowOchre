package com.example.yellowochre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Arrays;

public class CheckActivity extends AppCompatActivity {

    CheckBox cBox1, cBox2, cBox3, cBox4;
    Button bConfirm;
    private String correctChoices[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        final CheckBox cBox1 = findViewById(R.id.checkbox1);
        final boolean cBox1Answer = cBox1.isChecked();
        final CheckBox cBox2 = findViewById(R.id.checkbox2);
        boolean cBox2Answer = cBox2.isChecked();
        final CheckBox cBox3 = findViewById(R.id.checkbox3);
        boolean cBox3Answer = cBox3.isChecked();
        final CheckBox cBox4 = findViewById(R.id.checkbox4);
        boolean cBox4Answer = cBox4.isChecked();
        bConfirm = (Button) findViewById(R.id.button_confirm_next);
        final int newScore = QuizActivity.score;
        final CheckBox[] checkAll = new CheckBox[4];
        //correctChoices = new String[]{"Java", "Python"};
        final String checkone = cBox2.getText().toString();
        final String checktwo = cBox4.getText().toString();

        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Score in checkactivity before is " + newScore);
                if (!cBox1.isChecked() && cBox2.isChecked() && !cBox3.isChecked() && cBox4.isChecked()) {
                  //if(cBox2.getText().toString().equals("Java") && cBox2.getText().toString().equals("Python")){
                    QuizActivity.score++;
                    Toast.makeText(getApplicationContext(), "Score is : " + newScore, Toast.LENGTH_SHORT).show();
                } else if (cBox1.isChecked() || cBox3.isChecked()){
                    Toast.makeText(getApplicationContext(), "Please select one more answer ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please select two answers ", Toast.LENGTH_SHORT).show();
                }
                System.out.println("Score in checkactivity after is " + QuizActivity.score);
                Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
                intent.putExtra("RIGHT_ANSWER_COUNT", newScore);
                startActivity(intent);
            }
        });
    }
}