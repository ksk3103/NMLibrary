package com.example.khulood.nmlibrary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.khulood.nmlibrary.Navigation.toBitmap;

public class Profile_Activity extends AppCompatActivity {

    private ImageView img;
    private TextView Name, Course, Sid, Email, Book, Issuedate, Returndate, BookIssue, IssueNumber;
    RelativeLayout R1, R2;
    SqliteHelper sq;
    ArrayList<book> lst = new ArrayList<book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        sq = new SqliteHelper(this);

        String bookno = sq.idno();

        img = (ImageView) findViewById(R.id.img_id);
        Name = (TextView) findViewById(R.id.user);
        Course = (TextView) findViewById(R.id.course);
        Sid = (TextView) findViewById(R.id.sid_id);
        Email = (TextView) findViewById(R.id.email_id);
        Book = (TextView) findViewById(R.id.current_id);
        Issuedate = (TextView) findViewById(R.id.issue_id);
        Returndate = (TextView) findViewById(R.id.return_date);
        BookIssue = (TextView) findViewById(R.id.book_issue);
        IssueNumber = (TextView) findViewById(R.id.issue_number);
        R1 = (RelativeLayout) findViewById(R.id.book_issue_id);
        R2 = (RelativeLayout) findViewById(R.id.issue_history);


        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        final DatabaseAccess2 databaseAccess2 = DatabaseAccess2.getInstance(this);
        SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);

        String sid = myPrefs.getString("sid", "null");
        String name = myPrefs.getString("name", "null");
        String email = myPrefs.getString("email", "null");
        String course = myPrefs.getString("course", "null");
        final String title = myPrefs.getString("title", "null");
        String author = myPrefs.getString("author", "null");
        String issuedate = myPrefs.getString("issuedate", "null");
        String returndate = myPrefs.getString("returndate", "null");

        // Open the database
        databaseAccess.open();
        // Retrieve the selected image as byte[]
        byte[] data = databaseAccess.getImage(sid);
        // Convert to Bitmap
        Bitmap image = toBitmap(data);
        // Set to the imgPlace
        img.setImageBitmap(image);
        // Close the database
        databaseAccess.close();

        if (bookno!=null) { IssueNumber.setText(bookno); } else { IssueNumber.setText("0"); }

        if (myPrefs.contains("name")) Name.setText(name);
        if (myPrefs.contains("course")) Course.setText(course);
        if (myPrefs.contains("sid")) Sid.setText(sid);
        if (myPrefs.contains("email")) Email.setText(email);
        if (myPrefs.contains("title")) Book.setText(title + ", By " + author);
        if (myPrefs.contains("issuedate")) Issuedate.setText(issuedate);
        if (myPrefs.contains("returndate")) Returndate.setText(returndate);


        if (!myPrefs.contains("title") && !myPrefs.contains("issuedate") && !myPrefs.contains("returndate")) {
            BookIssue.setText("NO");
            Book.setText("No Book Issued ");
            Issuedate.setText("NA");
            Returndate.setText("NA");
        } else { BookIssue.setText("YES"); }

        R1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (BookIssue.getText()=="YES") {
                    lst.clear();
                    // Open the database
                    databaseAccess2.open();
                    lst = databaseAccess2.details("SELECT * FROM Books WHERE Book_name = ?", title);
                    //close database
                    databaseAccess2.close();
                    Intent i = new Intent(Profile_Activity.this, DisplayBook.class);
                    i.putExtra("book", lst);
                    startActivity(i);
                } else {
                    R1.setEnabled(false);
                    R1.setClickable(false);
                }
            }
        });

        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile_Activity.this, Books_issued.class);
                startActivity(i);
            }
        });

    }
}
