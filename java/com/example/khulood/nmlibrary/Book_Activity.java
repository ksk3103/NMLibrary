package com.example.khulood.nmlibrary;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khulood.nmlibrary.Adapter.RecyclerViewAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.RequiresApi;

public class Book_Activity extends Navigation {
    private ImageView BookImage;
    private TextView BookTitle, BookAuthor, BookPublisher, BookPublishDate, BookISBN, BookDescription, IssueStatus;
    private Button Issue, Cancel, Scan, BookMark;
    ArrayList<book> lstbook = new ArrayList<book>();
    SqliteHelper sqliteHelper;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_book_, null, false);
        drawer.addView(contentView, 0);
        final DatabaseAccess2 databaseAccess = DatabaseAccess2.getInstance(this);



        //Receive data
        Intent i = getIntent();
        final String Title = i.getExtras().getString("title");
        final String Author = i.getExtras().getString("author");
        byte[] Image = i.getExtras().getByteArray("image"); Bitmap image = toBitmap(Image);
        String Publisher = i.getExtras().getString("publisher");
        String PublishDate = i.getExtras().getString("publish_date");
        final String ISBN = i.getExtras().getString("isbn");
        String Description = i.getExtras().getString("description");
        final String Issue_Status = i.getExtras().getString("issue");
        lstbook = (ArrayList<book>) i.getSerializableExtra("lstbook");

        sqliteHelper = new SqliteHelper(this);

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final Date date = new Date();
        final String issuedate = dateFormat.format(date);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +10);
        Date todate1 = cal.getTime();
        final String returndate = dateFormat.format(todate1);


        final SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);
        final String title = myPrefs.getString("title", "null");

        final String sid = myPrefs.getString("sid", "null");


        BookTitle = (TextView) findViewById(R.id.book_title_id);
        BookAuthor = (TextView) findViewById(R.id.author_id);
        BookImage = (ImageView) findViewById(R.id.book_imd_id);
        BookPublisher = (TextView) findViewById(R.id.publish_id);
        BookPublishDate = (TextView) findViewById(R.id.publish_date_id);
        BookISBN = (TextView) findViewById(R.id.isbn_id);
        BookDescription = (TextView) findViewById(R.id.desc_id);
        IssueStatus = (TextView) findViewById(R.id.issue_id);
        Issue = (Button) findViewById(R.id.issue);
        Cancel = (Button) findViewById(R.id.cancel);
        Scan = (Button) findViewById(R.id.scan);
        BookMark = (Button) findViewById(R.id.bookmark);

        if(Issue_Status == "Unavailable" && IssueStatus.getText()!="Book Issued") {
            Issue.setVisibility(View.GONE);
            Cancel.setVisibility(View.GONE);
            Scan.setVisibility(View.GONE);
            BookMark.setVisibility(View.VISIBLE);
        }

        if(myPrefs.contains("title") && title.equals(Title)) {
            Scan.setVisibility(View.GONE);
            Issue.setVisibility(View.GONE);
            BookMark.setVisibility(View.GONE);
            Cancel.setVisibility(View.VISIBLE);
            IssueStatus.setText("Book Issued");
        } else { IssueStatus.setText("Book Available"); }
        //setting values

        BookTitle.setText(Title);
        BookAuthor.setText(Author);
        BookImage.setImageBitmap(image);
        BookPublisher.setText(Publisher);
        BookPublishDate.setText(PublishDate);
        BookISBN.setText(ISBN);
        BookDescription.setText(Description);


        final String bk = "Book Issued";
        final String av = "Book Available";

        Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myPrefs.getString("title",null) != null){
                    Toast.makeText(getApplicationContext(), "Book already issued, please return previous book", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(Book_Activity.this, ScanActivity.class);
                    startActivity(i);
                    onResume();
                    Scan.setVisibility(View.GONE);
                    Issue.setVisibility(View.VISIBLE);
                }
            }
        });

        Issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myPrefs.getString("title","null") != null) {

                    String barcode = myPrefs.getString("barcode", null);
                    if ((barcode != null && sid != null) && (barcode.equals(sid))) {
                        databaseAccess.open();
                        databaseAccess.issue("Unavailable", ISBN);
                        SharedPreferences.Editor editor = myPrefs.edit();
                        editor.putString("title", Title);
                        editor.putString("author", Author);
                        editor.putString("issuedate", issuedate);
                        editor.putString("returndate", returndate);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "Book Issued! Please collect from Library", Toast.LENGTH_LONG).show();
                        Issue.setVisibility(View.GONE);
                        Cancel.setVisibility(View.VISIBLE);
                        IssueStatus.setText(bk);
                        databaseAccess.close();

                    } else {

                        if (barcode != null && !barcode.equals(sid)) {
                            Toast.makeText(getApplicationContext(), "Please Scan Correct ID", Toast.LENGTH_LONG).show();
                            Issue.setVisibility(View.GONE);
                            Scan.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final String returnedat = dateFormat.format(date);
                    databaseAccess.open();
                    databaseAccess.issue("Available", ISBN);
                    sqliteHelper.bookinsert(sid, Title, Author, issuedate, returndate, returnedat);
                    Toast.makeText(getApplicationContext(), "Issue Cancelled! Please return book to Library if collected", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = myPrefs.edit();
                    editor.remove("title"); editor.remove("author"); editor.remove("issuedate"); editor.remove("returndate");
                    editor.apply();
                    myPrefs.edit().apply();
                    databaseAccess.close();
                    IssueStatus.setText(av);
                    Cancel.setVisibility(View.GONE);
                    Issue.setVisibility(View.GONE);
                    Scan.setVisibility(View.VISIBLE);
            }
        });

        BookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqliteHelper.bookmark(title);
                Toast.makeText(getApplicationContext(), "Bookmarked Successful. You will be notified once the book becomes available", Toast.LENGTH_LONG).show();
            }
        });

    }

}
