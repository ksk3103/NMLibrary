package com.example.khulood.nmlibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khulood.nmlibrary.forms.AboutActivity;
import com.example.khulood.nmlibrary.forms.FeedbackActivity;
import com.example.khulood.nmlibrary.forms.RequestActivity;
import com.example.khulood.nmlibrary.forms.ReviewActivity;


public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;
    ImageView immag;
    TextView txtName;
    TextView txtsid;
    TextView txtcourse;
    TextView txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        immag = (ImageView) headerView.findViewById(R.id.profile);
        txtName = (TextView) headerView.findViewById(R.id.n);
        txtsid = (TextView) headerView.findViewById(R.id.s);
        txtcourse = (TextView) headerView.findViewById(R.id.c);
        txtemail = (TextView) headerView.findViewById(R.id.e);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);

            String name = myPrefs.getString("name", "null");
            String course = myPrefs.getString("course", "null");
            String sid = myPrefs.getString("sid", "null");
            String email = myPrefs.getString("email", "null");

        // Open the database
        databaseAccess.open();
        // Retrieve the selected image as byte[]
        byte[] data = databaseAccess.getImage(sid);
        // Convert to Bitmap
        Bitmap image = toBitmap(data);
        // Set to the imgPlace
        immag.setImageBitmap(image);
        // Close the database
        databaseAccess.close();

            if (myPrefs.contains("name")) txtName.setText(name);
            if (myPrefs.contains("course")) txtcourse.setText(course);
            if (myPrefs.contains("sid")) txtsid.setText(sid);
            if (myPrefs.contains("email")) txtemail.setText(email);

            immag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Navigation.this, Profile_Activity.class);
                    startActivity(i);
                }
            });

    }

    public static Bitmap toBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feedback) {

            Intent i = new Intent(this, FeedbackActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_logout) {

            //clearing data from Shared preferences
            final SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = myPrefs.edit();
            editor.clear();
            editor.apply();

            
            //After logout redirect user to login page
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            //Closing all activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //Adding flag to start new activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Starting login activity
            this.startActivity(i);
            finish();

        } else if (id == R.id.nav_request) {

            Intent i = new Intent(this, RequestActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_review) {

        Intent i = new Intent(this, ReviewActivity.class);
        startActivity(i);

        } else if (id == R.id.nav_about) {

            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
