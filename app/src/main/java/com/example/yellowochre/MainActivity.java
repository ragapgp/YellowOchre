/* This activity creates main page which shows login
* details username, password, Login and Register
* links with beautiful background and a image on screen.
*/

package com.example.yellowochre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Mention the duration splash screen will reload
    private static int SPLASH_TIME = 450000000;
    EditText eUsername, ePassword;
    Button bLogin;
    TextView tRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);

        // Declare fields
        eUsername = (EditText)findViewById(R.id.editText_username);
        ePassword = (EditText)findViewById(R.id.editText_password);
        bLogin = (Button) findViewById(R.id.button_login);
        tRegister = (TextView) findViewById(R.id.textView_register);

        //Register functionality
        tRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Login functionality
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eUsername.getText().toString().length() > 0 &&
                        ePassword.getText().toString().length() > 0) {
                    Toast.makeText(getApplicationContext(),
                            "Login is Successful...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}