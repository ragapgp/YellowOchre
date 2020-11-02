/* This activity creates main page which shows login
* details username, password, Login and Register
* links with beautiful background and a image on screen.
*/

package com.example.yellowochre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // Mention the duration splash screen will reload
    private static int SPLASH_TIME = 450000000;
    EditText eUsername, ePassword;
    Button bLogin;
    TextView tRegister;
    String sUsername, sPassword;
    public static final String prefName = "userpref";
    SessionManager sessionManager;
    SharedPreferences sph;

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

        sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        final SharedPreferences.Editor edi = sph.edit();
        sessionManager = new SessionManager(getApplicationContext());
        // Declare fields
        eUsername = (EditText)findViewById(R.id.editText_username);
        ePassword = (EditText)findViewById(R.id.editText_password);
        bLogin = (Button) findViewById(R.id.button_login);
        tRegister = (TextView) findViewById(R.id.textView_register);

        //sph = getApplicationContext().getSharedPreferences(prefName, Context.MODE_PRIVATE);
        //sph = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Map<String, ?> allEntries = sph.getAll();
        System.out.println("All entries are :" + allEntries);
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
            System.out.println("map values" + entry.getKey() + ": " + entry.getValue().toString());
        }

        final String chkusr = sph.getString("sUsername", null);
        System.out.println("chkusr is " + chkusr);
        String user = eUsername.getText().toString();

        final String sSavedUsername = sph.getString(user, null);

        //Register functionality
        tRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (sessionManager.getLogin()){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        // Login functionality
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check for saved data
                String user = eUsername.getText().toString().trim();
                String pwd = ePassword.getText().toString().trim();

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                String logdate = sdf.format(new Date());
                //Calendar logdate=Calendar.getInstance();
                //edit.putLong(Preference.KEY_FIRST_DATE, first.get(Calendar.DATE););
                edi.putString("time", logdate);
                edi.commit();
                sessionManager.setTime(logdate);
                System.out.println("User in session " + sessionManager.getUsername().contains(user));

                if (user.length() > 0 &&
                        pwd.length() > 0) {
                    if (sessionManager.getUsername().contains(user)) {
                        String getuser = sessionManager.getUsername();
                        Toast.makeText(getApplicationContext(), "Login is successfull" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                        intent.putExtra("updateuser", getuser);
                        startActivity(intent);
                        finish();
                    } else {
                        SharedPreferences.Editor edi = sph.edit();
                        edi.putString(sUsername, user);
                        edi.commit();
                        Toast.makeText(getApplicationContext(), "Login is successfull" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                        sessionManager.setUsername(user);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}