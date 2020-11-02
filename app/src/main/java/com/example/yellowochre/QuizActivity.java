package com.example.yellowochre;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/* QuizActivity class which creates multiple choice questions and answers.*/
public class QuizActivity extends AppCompatActivity {

    public static int score = 0;
    public static int questionCount = 1;
    static final private int maxQuestionCount = 5;
    private static RadioButton rbChosen;
    private static RadioGroup rg;
    private static RadioButton rbSelected;
    private static RadioButton rbOption1;
    private static RadioButton rbOption2;
    private static RadioButton rbOption3;
    private static RadioButton rbOption4;
    private static Button bSubmit;
    private static TextView tQuestion;
    private static TextView tQuestionNo;
    private String aAnswer;
    private String correctAnswers[];
    private boolean answered;

    private int curQuestionInd = 0;
    private int curAnswerInd = 0;
    private ArrayList<Question> questions;
    private Question qQuestion = new Question();
    //private int qSize = qQuestion.getQuestion().length();

    //shared preferences variables
    String squestion, soption1, soption2, soption3, soption4;
    String squestionOne = "QuestionOne";

    String soptionOne = "Q1option";
    String soptionOne1 = "Q1OptionOne";
    String soptionOne2 = "Q1OptionTwo";
    String soptionOne3 = "Q1OptionThree";
    String soptionOne4 = "Q1OptionFour";

    String soptionTwo = "Q2option";
    String squestionTwo = "QuestionTwo";
    String soptionTwo1 = "Q2OptionOne";
    String soptionTwo2 = "Q2OptionTwo";
    String soptionTwo3 = "Q2OptionThree";
    String soptionTwo4 = "Q2OptionFour";

    String soptionThree = "Q3option";
    String squestionThree = "QuestionThree";
    String soptionThree1 = "Q3OptionOne";
    String soptionThree2 = "Q3OptionTwo";
    String soptionThree3 = "Q3OptionThree";
    String soptionThree4 = "Q3OptionFour";

    String soptionFour = "Q4option";
    String squestionFour = "QuestionFour";
    String soptionFour1 = "Q4OptionOne";
    String soptionFour2 = "Q4OptionTwo";
    String soptionFour3 = "Q4OptionThree";
    String soptionFour4 = "Q4OptionFour";

    //SharedPreferences sp;
    public static final String prefName = "userpref";
    SessionManager sessionManager;
    String sScore;
    //SharedPreferences sph;

    //SharedPreferences sph = PreferenceManager.getDefaultSharedPsph = getSharedPreferences(prefName, Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        sessionManager = new SessionManager(getApplicationContext());
        SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edi = sph.edit();
        this.initialize();
    }

    private void initialize() {

        rg = (RadioGroup) findViewById(R.id.radio_group);
        rbOption1 = (RadioButton) findViewById(R.id.radio_button1);
        rbOption2 = (RadioButton) findViewById(R.id.radio_button2);
        rbOption3 = (RadioButton) findViewById(R.id.radio_button3);
        rbOption4 = (RadioButton) findViewById(R.id.radio_button4);
        tQuestion = (TextView) findViewById(R.id.question);
        bSubmit = (Button) findViewById((R.id.button_confirm_next));
        tQuestionNo = (TextView) findViewById(R.id.questionNumber);

        squestion = tQuestion.getText().toString();
        soption1 = rbOption1.getText().toString();
        soption2 = rbOption2.getText().toString();
        soption3 = rbOption3.getText().toString();
        soption4 = rbOption4.getText().toString();

        questions = new ArrayList<Question>();

        questions.add(new Question("What is Splunk used for?", "Logs",
                "Deployment", "Security", "Analytics", 4, 0));
        //questions.add(new Question("Which two technologies are more demanding in 2020?", "Linux",
                //"Java", "ASP", "Python", "Python", 0));
        questions.add(new Question("Who is the founder of Python?", "Mark Zuckerberg",
                "Bill Gates", "Elon Musk", "Guido Van Rossum",4, 0));
        questions.add(new Question("Which protocol is used more?", "REST",
                "SOAP", "HTTP", "HTTPS", 1, 0));
        questions.add(new Question("Kotlin was created to solve deeper challenges of", "JetBrains",
                "Eclipse", "SublimeText", "Atom", 1,0));

        correctAnswers = new String[]{"Analytics", "Guido Van Rossum", "REST", "JetBrains"};
        //SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        sessionManager = new SessionManager(getApplicationContext());
        SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edi = sph.edit();

        sph = getSharedPreferences(prefName, MODE_PRIVATE);
        squestionOne = sph.getString(squestionOne, null);
        soptionOne = sph.getString(soptionOne, null);
        squestionTwo = sph.getString(squestionTwo, null);
        soptionTwo = sph.getString(soptionTwo, null);
        squestionThree = sph.getString(squestionThree, null);
        soptionThree = sph.getString(soptionThree, null);
        squestionFour = sph.getString(squestionFour, null);
        soptionFour = sph.getString(soptionFour, null);

        this.showQuestion(questionCount);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int radioChoice = rg.getCheckedRadioButtonId();
                rbChosen = findViewById(radioChoice);
                checkSubmit(rbChosen);
                createAlert();
            }
        });
}

    private void createAlert(){
        AlertDialog confirmAnswerDialog = new AlertDialog.Builder(this)
                .setTitle("Confirm Answer")
                .setMessage("Do you wish to submit your selected answer!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int ig = rg.getCheckedRadioButtonId();
                        System.out.println("questionCount is "+ questionCount);
                        System.out.println("score is "+ score);
                        if (ig == -1) {
                            Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                        }
                        if (questionCount == 5){
                            Intent intent = new Intent(getApplicationContext(), CheckActivity.class);
                            intent.putExtra("RIGHT_ANSWER_COUNT", score);
                            startActivity(intent);
                        } else {
                            //questionCount++;
                            checkAnswer();
                            //saveSession();
                            next();
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    private void showQuestion(int questionCount) {
        tQuestion.setText(questions.get(curQuestionInd).getQuestion());
        rbOption1.setText(questions.get(curQuestionInd).getOption1());
        rbOption2.setText(questions.get(curQuestionInd).getOption2());
        rbOption3.setText(questions.get(curQuestionInd).getOption3());
        rbOption4.setText(questions.get(curQuestionInd).getOption4());
        tQuestionNo.setText("Question " + questionCount + "/" + maxQuestionCount);
        rg.clearCheck();
    }

   private void next(){
       sessionManager = new SessionManager(getApplicationContext());
       SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
       SharedPreferences.Editor edi = sph.edit();
        System.out.println("questionCount in next is : " + questionCount);
        if (questionCount == 4) {
            questionCount++;
            tQuestionNo.setText("Question " + questionCount + "/" + maxQuestionCount);
            answered = false;
            bSubmit.setText("Confirm");
            sessionManager.setScore(score);
            edi.putInt(sScore, score);
            edi.commit();
            System.out.println("Score when qc" +questionCount+ "is : " + score);
            System.out.println("sScore when qc" +questionCount+ "is : " + sScore);

            Intent intent = new Intent(getApplicationContext(), CheckActivity.class);
            intent.putExtra("RIGHT_ANSWER_COUNT", score);
            PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().clear().apply();
            edi.clear();
            edi.commit();
            sph.edit().remove("tQuestionNo").commit();
            sph.edit().remove("squestion").commit();

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            startActivity(intent);
        }
        else if (questionCount < maxQuestionCount){
            qQuestion = questions.get(questionCount);
            tQuestion.setText(qQuestion.getQuestion());
            rbOption1.setText(qQuestion.getOption1());
            rbOption2.setText(qQuestion.getOption2());
            rbOption3.setText(qQuestion.getOption3());
            rbOption4.setText(qQuestion.getOption4());

            questionCount++;
            tQuestionNo.setText("Question " + questionCount + "/" + maxQuestionCount);
            answered = false;
            bSubmit.setText("Confirm");
            sessionManager.setScore(score);
            edi.putInt(sScore, score);
            edi.commit();
            System.out.println("Score when qc " +questionCount+ "is : " + score);
            System.out.println("sScore when qc " +questionCount+  "is : " + sScore);
        }
    }

    private void checkAnswer(){
        sessionManager = new SessionManager(getApplicationContext());
        SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edi = sph.edit();
        answered = true;
        rbSelected = findViewById(rg.getCheckedRadioButtonId());
        System.out.println("rbselected is "+ rbSelected.getText().toString());

        if (questionCount == 1) {
            edi.putString(squestionOne, tQuestion.getText().toString());
            edi.putString(soptionOne, rbSelected.getText().toString());
            edi.commit();
            System.out.println(questionCount + "Question and " +questionCount+ "Choice is saved");
            Toast.makeText(this, questionCount+"Question and Choice is saved: " + score, Toast.LENGTH_SHORT).show();
        }
        if (questionCount == 2) {
            edi.putString(squestionTwo, tQuestion.getText().toString());
            edi.putString(soptionTwo, rbSelected.getText().toString());
            edi.commit();
            System.out.println(questionCount + "Question and " +questionCount+ "Choice is saved");
            Toast.makeText(this, questionCount+"Question and Choice is saved: " + score, Toast.LENGTH_SHORT).show();
        }
        if (questionCount == 3) {
            edi.putString(squestionThree, tQuestion.getText().toString());
            edi.putString(soptionThree, rbSelected.getText().toString());
            edi.commit();
            System.out.println(questionCount + "Question and " +questionCount+ "Choice is saved");
            Toast.makeText(this, questionCount+"Question and Choice is saved: " + score, Toast.LENGTH_SHORT).show();
        }
        if (questionCount == 4) {
            edi.putString(squestionFour, tQuestion.getText().toString());
            edi.putString(soptionFour, rbSelected.getText().toString());
            edi.commit();
            System.out.println(questionCount + "Question and " +questionCount+ " Choice is saved");
            Toast.makeText(this, questionCount+"Question and Choice is saved: " + score, Toast.LENGTH_SHORT).show();
        }

        if (Arrays.asList(correctAnswers).contains(rbSelected.getText().toString())) {
            score++;
            Toast.makeText(this, "Score is : " + score, Toast.LENGTH_SHORT).show();
        }
    }

    public void checkSubmit(View view){
        int radioChoice = rg.getCheckedRadioButtonId();
        rbChosen = findViewById(radioChoice);
        if (radioChoice == -1) {
            Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Selected answer is : " + rbChosen.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}