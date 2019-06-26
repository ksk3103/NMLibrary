package com.example.khulood.nmlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ResetPasswordActivity extends AppCompatActivity {

    private EditText inputEmail;
    private TextView passHint;
    private Button btnReset, btnBack;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail = (EditText) findViewById(R.id.email);
        passHint = (TextView) findViewById(R.id.passhint);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        btnBack = (Button) findViewById(R.id.btn_back);
        sqliteHelper = new SqliteHelper(this);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString();

                if(sqliteHelper.isEmailExists(email)) {

                    String passhint = sqliteHelper.getPassHint(email);
                    passHint.setText(passhint);
                    passHint.setVisibility(View.VISIBLE);

                } else {

                    Toast.makeText(getApplicationContext(), "Email id not found! Please Register ", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}