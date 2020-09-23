package com.example.yellowochre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    EditText eUsername, eLoginPwd;
    Button bMainLogin;
    TextView tLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eUsername = (EditText) findViewById(R.id.editText_username);
        eLoginPwd = (EditText) findViewById(R.id.editText_password);
        tLogin = (TextView) findViewById(R.id.textView_login);
    }
}