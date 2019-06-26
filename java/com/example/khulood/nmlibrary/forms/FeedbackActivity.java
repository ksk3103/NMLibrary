package com.example.khulood.nmlibrary.forms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khulood.nmlibrary.R;

import java.util.HashMap;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button btn;
    EditText edtemail, edtsid, edtFeedback;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        btn = (Button) findViewById(R.id.btnFeedback);
        edtemail = (EditText) findViewById(R.id.edtEmail);
        edtsid = (EditText) findViewById(R.id.edtSID);
        edtFeedback = (EditText) findViewById(R.id.edtFeedback);

        // Initializing Queue for Volley
        queue = Volley.newRequestQueue(getApplicationContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtemail.getText().toString().trim().length() > 0 && edtsid.getText().toString().trim().length() > 0 && edtFeedback.getText().toString().trim().length() > 0) {
                    postData(edtemail.getText().toString().trim(), edtsid.getText().toString().trim(), edtFeedback.getText().toString().trim());
                } else {
                    Snackbar.make(view, "Required Fields Missing", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }


    public void postData(final String email, final String id, final String feedbck) {

        progressDialog.show();
        StringRequest request = new StringRequest(
                Request.Method.POST,
                Constants.feedback,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "Response: " + response);
                        if (response.length() > 0) {
                            Snackbar.make(btn, "Successfully Posted", Snackbar.LENGTH_LONG).show();
                            edtemail.setText(null);
                            edtsid.setText(null);
                            edtFeedback.setText(null);
                        } else {
                            Snackbar.make(btn, "Try Again", Snackbar.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Snackbar.make(btn, "Error while Posting Data", Snackbar.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Constants.Email, email);
                params.put(Constants.SID, id);
                params.put(Constants.feedbackfield, feedbck);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

}
