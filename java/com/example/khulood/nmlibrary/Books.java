package com.example.khulood.nmlibrary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.khulood.nmlibrary.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class Books extends Navigation implements View.OnClickListener {
    CardView novel, exam, textbook, reference;
    ArrayList<book> lstbook = new ArrayList<book>();
    RecyclerViewAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_books, null, false);
        drawer.addView(contentView, 0);

        //defining cards
        novel = (CardView) findViewById(R.id.novel_books);
        exam = (CardView) findViewById(R.id.exam_books);
        textbook = (CardView) findViewById(R.id.text_books);
        reference = (CardView) findViewById(R.id.reference_books);
        // add click listener to the cards
        novel.setOnClickListener(this);
        exam.setOnClickListener(this);
        textbook.setOnClickListener(this);
        reference.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;
        final DatabaseAccess2 databaseAccess = DatabaseAccess2.getInstance(this);

        switch (v.getId()) {
            case R.id.novel_books :
                lstbook.clear();
                // Open the database
                databaseAccess.open();
                lstbook = databaseAccess.details("SELECT * FROM books WHERE Category = ?", "Novel");
                //close database
                databaseAccess.close();
                i = new Intent(this,DisplayBook.class);
                i.putExtra("book", lstbook);
                startActivity(i); break;

            case R.id.exam_books :
                lstbook.clear();
                // Open the database
                databaseAccess.open();
                lstbook = databaseAccess.details("SELECT * FROM books WHERE Category = ?", "Exam");
                //close database
                databaseAccess.close();
                i = new Intent(this,DisplayBook.class);
                i.putExtra("book", lstbook);
                startActivity(i); break;

            case R.id.text_books :
                lstbook.clear();
                // Open the database
                databaseAccess.open();
                lstbook = databaseAccess.details("SELECT * FROM books WHERE Category = ?", "Textbook");
                //close database
                databaseAccess.close();
                i = new Intent(this,DisplayBook.class);
                i.putExtra("book", lstbook);
                startActivity(i); break;

            case R.id.reference_books :
                lstbook.clear();
                // Open the database
                databaseAccess.open();
                lstbook = databaseAccess.details("SELECT * FROM books WHERE Category = ?", "Reference");
                //close database
                databaseAccess.close();
                i = new Intent(this,DisplayBook.class);
                i.putExtra("book", lstbook);
                startActivity(i); break;

            default:break;
        }
    }
}