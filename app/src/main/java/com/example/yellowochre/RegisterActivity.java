/* This activity creates register page which shows registration
 * details first name, password, family name, date of birth,
 * email, Login and Register links with beautiful background
 * and a image on screen.
 */

package com.example.yellowochre;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RegisterActivity extends AppCompatActivity {
    EditText eFirstName, eFamilyName, eDateOfBirth, eEmail, eRegPassword;
    Button bRegister;
    TextView tLogin;
    // Regex for Email validation
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    // Fields validation
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            fieldsValidation();

            //date check
            String dateCheck = charSequence.toString();
            boolean check = true;
       /*     if (dateCheck.length()==2 && i1 ==0) {
                if (Integer.parseInt(dateCheck) < 1 || Integer.parseInt(dateCheck)>12) {
                    check = false;
                } else {
                    dateCheck+="/";
                    eDateOfBirth.setText(dateCheck);
                    eDateOfBirth.setSelection(dateCheck.length());
                }
            }
            else if (dateCheck.length()==5 && i1 ==0) {
                String month = dateCheck.substring(3);
                int curMonth = Calendar.getInstance().get(Calendar.MONTH);
                dateCheck+="/";
                eDateOfBirth.setText(dateCheck);
                eDateOfBirth.setSelection(dateCheck.length());
                if (Integer.parseInt(month) < curMonth) {
                    check = false;
                    System.out.println("month check is : "+check);
                }
            } else if (dateCheck.length()!=6) {
                check = false;
                System.out.println("month else check is : "+check);
            }
            else if (dateCheck.length()==10 && i1 ==0) {
                String year = dateCheck.substring(6);
                int curYear = Calendar.getInstance().get(Calendar.YEAR);
                if (Integer.parseInt(year) < curYear) {
                    check = false;
                    System.out.println("year check is : "+check);
                }
            } else if (dateCheck.length()!=10) {
                check = true;
                System.out.println("year else check is : "+check);
            }

            if (check) {
                eDateOfBirth.setError("Enter a valid date: MM/DD/YYYY");
            } else {
                eDateOfBirth.setError(null);
            }*/

            bRegister.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    public RegisterActivity() throws ParseException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eFirstName = (EditText) findViewById(R.id.editText_firstname);
        eFamilyName = (EditText) findViewById(R.id.editText_familyname);
        eDateOfBirth = (EditText) findViewById(R.id.editText_dob);
        eEmail = (EditText) findViewById(R.id.editText_email);
        eRegPassword = (EditText) findViewById(R.id.editText_regpassword);
        bRegister = (Button) findViewById(R.id.button_register);
        tLogin = (TextView) findViewById(R.id.textView_login);

        //set the register button to disable
        bRegister.setEnabled(false);

        eFirstName.addTextChangedListener(textWatcher);
        eFamilyName.addTextChangedListener(textWatcher);
        eDateOfBirth.addTextChangedListener(textWatcher);
        eEmail.addTextChangedListener(textWatcher);
        eRegPassword.addTextChangedListener(textWatcher);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fieldsValidation();
                Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
                dateCheck();
                if (eFirstName.getText().toString().length() > 3 &&
                        eFirstName.getText().toString().length() < 30 &&
                        eFamilyName.getText().toString().length() > 0  &&
                        eDateOfBirth.getText().toString().length() > 0 &&
                        eEmail.getText().toString().length() > 0 &&
                        eRegPassword.getText().toString().length() > 0 ) {

                    System.out.println("No fields are null, can register now.");
                    bRegister.setEnabled(true);
                    startActivity(registerIntent);
                    Toast.makeText(getApplicationContext(),
                            "Registration is Successful...",Toast.LENGTH_SHORT).show();
                }
                else {
                    System.out.println("Fields are still null, cannot register yet.");
                    bRegister.setEnabled(false);
                }
            }
        });

        tLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    private String dateCheck(){
        // Date validation
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
        Date newDate = null;
        String eDate = eDateOfBirth.getText().toString();

        try {
            newDate = dateFormat.parse(eDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateFormat = new SimpleDateFormat("MM/DD/YYYY");
        String newDateString = dateFormat.format(newDate);
        System.out.println(newDateString);
        return newDateString;
    }

    // All Fields data validation
    public void fieldsValidation() {
        String fname = eFirstName.getText().toString();
        String fmname = eFamilyName.getText().toString();
        String dob = eDateOfBirth.getText().toString();
        String email = eEmail.getText().toString();
        String pwd = eRegPassword.getText().toString();


        if (fname.length() < 0 || fname.isEmpty()) {
            eFirstName.setError("First Name is required");
            System.out.println("FName required");
        } else if (fname.length() < 3) {
            eFirstName.setError("First Name cannot be less than 3 characters");
            System.out.println("FName less than 3 chars");
        } else if (fname.length() > 30) {
            eFirstName.setError("First Name cannot be more than 30 characters");
            System.out.println("FName more than 30 chars");
        }
        else {
            eFirstName.setError(null);
            System.out.println("FName set error null");
        }

        if (fmname.length() < 0 || fmname.isEmpty()) {
            eFamilyName.setError("Family Name is required");
            System.out.println("Family Name required");
        } else {
            eFamilyName.setError(null);
            System.out.println("Fam Name set error null");
        }

        if (email.length() < 0 || email.isEmpty()) {
            eEmail.setError("Email is required");
            System.out.println("Email required");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(eEmail.getText().toString()).matches()){
            eEmail.setError("Please enter a valid E-Mail Address!");
            System.out.println("Email invalid");
        } else {
            eEmail.setError(null);
            System.out.println("Email set error null");
        }

        if (dob.length() < 0 || dob.isEmpty()) {
            eDateOfBirth.setError("Date of Birth is empty");
        }
        else {
            eDateOfBirth.setError(null);
        }

        if (pwd.length() < 0 || pwd.isEmpty()) {
            eRegPassword.setError("Password is required");
        } else {
            eRegPassword.setError(null);
        }
    }
}