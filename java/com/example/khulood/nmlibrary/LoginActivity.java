package com.example.khulood.nmlibrary;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextSID;
    EditText editTextPassword;

    TextView resetpass;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutSID;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

        //set click event of login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String SID = editTextSID.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, SID, Password, null));

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Toast.makeText(getApplicationContext(), "Successfully Logged in!", Toast.LENGTH_LONG).show();

                        databaseAccess.open();
                        String name = databaseAccess.getUserNameFromSID(SID);
                        String course = databaseAccess.getUserCourseFromSID(SID);
                        String email = sqliteHelper.getEmailFromSID(SID);
                        databaseAccess.close();

                        SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = myPrefs.edit();
                        editor.putString("name", name);
                        editor.putString("course", course);
                        editor.putString("sid", SID);
                        editor.putString("email", email);
                        editor.remove("barcode");
                        editor.apply();
                        //User Logged in Successfully Launch You home screen activity
                        Intent intent=new Intent(LoginActivity.this,Dashboard.class);
                        startActivity(intent);
                        finish();
                    } else if (sqliteHelper.ifPasswordincorrect(SID)) {
                        Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
                    } else {

                        //User Logged in Failed
                        Toast.makeText(getApplicationContext(), "Not Registered", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            startActivity(i);
            }
        });


    }

    //this method used to set Create account TextView text and click event( multipal colors
    // for TextView yet not supported in Xml so i have done it programmatically)
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#424242'>Don't have account yet? </font><font color='#0c0099'>Create One</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextSID = (EditText) findViewById(R.id.sid);
        editTextPassword = (EditText) findViewById(R.id.password);
        resetpass = (TextView) findViewById(R.id.reset_password);
        textInputLayoutSID = (TextInputLayout) findViewById(R.id.textInputLayoutSid);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    //This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String SID = editTextSID.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for Email field
        if (!Patterns.PHONE.matcher(SID).matches()) {
            valid = false;
            textInputLayoutSID.setError("Please enter valid Student ID!");
        } else {
            valid = true;
            textInputLayoutSID.setError(null);
        }

        //Handling validation for Password field
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

        return valid;
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try{
                Thread.sleep(Snackbar.LENGTH_LONG);
                Intent intent=new Intent(LoginActivity.this,Dashboard.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


}