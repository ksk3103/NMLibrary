package com.example.khulood.nmlibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import info.androidhive.barcode.BarcodeReader;


public class RegisterActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextEmail;
    EditText editTextSID;
    EditText editTextPassword;
    EditText editTextPassHint;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutSID;
    TextInputLayout textInputLayoutPassword;
    TextInputLayout textInputLayoutPassHint;
    TextInputLayout textInputLayoutScan;

    //Declaration Button
    Button buttonRegister;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);
        BarcodeScanner();
        initTextViewLogin();
        initViews();


        final SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);


        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String barcode = myPrefs.getString("barcode", "null");
                    String Email = editTextEmail.getText().toString();
                    String SID = editTextSID.getText().toString();
                    String Password = editTextPassword.getText().toString();
                    String PassHint = editTextPassHint.getText().toString();
                    databaseAccess.open();

                    //Check in the database is there any user associated with  this email

                    if (!sqliteHelper.ifUserRegistered(SID) && databaseAccess.ifUserExists(SID) && !sqliteHelper.isEmailExists(Email) && (barcode != null && barcode.equals(SID))) {

                        sqliteHelper.registerUser(new User(null, Email, SID, Password, PassHint));
                        Toast.makeText(getApplicationContext(), "User created successfully! Please Login ", Toast.LENGTH_LONG).show();

                        thread.start();


                    } else {
                        if (sqliteHelper.ifUserRegistered(SID)) {

                            Toast.makeText(getApplicationContext(), "User already registered ", Toast.LENGTH_LONG).show();


                        } else {
                            if (sqliteHelper.isEmailExists(Email)) {

                                Toast.makeText(getApplicationContext(), "Email Id already in use ", Toast.LENGTH_LONG).show();
                            } else {
                                if (!databaseAccess.ifUserExists(SID)) {

                                    Toast.makeText(getApplicationContext(), "User doesn't exists in college ", Toast.LENGTH_LONG).show();

                                } else {
                                    if (barcode != null && !barcode.equals(SID)) {
                                        Toast.makeText(getApplicationContext(), "Wrong Id Scanned ", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error Occurred. Please Try Again ", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }

                    }
                    databaseAccess.close();
                }


            }
        });
    }

    private void BarcodeScanner() {
        TextView textViewScanner = (TextView) findViewById(R.id.scanner);
        textViewScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });

    }


    //this method used to set Login TextView click event
    private void initTextViewLogin() {
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.remove("barcode");
        editor.apply();
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextSID = (EditText) findViewById(R.id.sid);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassHint = (EditText) findViewById(R.id.passwordhint);
        textInputLayoutSID = (TextInputLayout) findViewById(R.id.textInputLayoutSID);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassHint = (TextInputLayout) findViewById(R.id.textInputLayoutPasswordHint);
        textInputLayoutScan = (TextInputLayout) findViewById(R.id.textInputLayoutScan);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);
        //Get values from EditText fields
        String Email = editTextEmail.getText().toString();
        String SID = editTextSID.getText().toString();
        String Password = editTextPassword.getText().toString();
        String PassHint = editTextPassHint.getText().toString();
        String barcode = myPrefs.getString("barcode", null);

        if (SID.isEmpty()) {
            valid = false;
            textInputLayoutSID.setError("Please Student ID!");
        } else {
            if (SID.length() == 11) {
                valid = true;
                textInputLayoutSID.setError(null);
            } else {
                valid = true;
                textInputLayoutSID.setError("Please enter valid Student ID!");
            }
        }

        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 3) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is too short!");
            }
        }

        if (PassHint.isEmpty()) {
            valid = false;
            textInputLayoutPassHint.setError("Please enter Password Hint!");
        }

        if (barcode == null) {
            valid = false;
            textInputLayoutScan.setError("Please Scan Student ID!");
        }

        //Handling validation for UserName field
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }
        return valid;
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                Thread.sleep(Snackbar.LENGTH_LONG);
                SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.remove("barcode");
                editor.apply();
                RegisterActivity.this.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onResume() {
        SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);
        String barcode = myPrefs.getString("barcode", "null");
        if (myPrefs.contains("barcode")) editTextSID.setText(barcode);
        super.onResume();
    }
}