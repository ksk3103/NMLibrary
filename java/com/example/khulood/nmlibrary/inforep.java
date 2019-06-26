package com.example.khulood.nmlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;


public class inforep extends Navigation implements View.OnClickListener {
    private CardView QuesPaper, BookReview, Bibliographies, JournalArticle, Newsletter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_inforep, null, false);
        drawer.addView(contentView, 0);

        QuesPaper= (CardView) findViewById(R.id.ques_paper);
        BookReview= (CardView) findViewById(R.id.book_review);
        Bibliographies= (CardView) findViewById(R.id.bibliographies);
        JournalArticle= (CardView) findViewById(R.id.jour_article);
        Newsletter= (CardView) findViewById(R.id.newsletter);

        QuesPaper.setOnClickListener(this);
        BookReview.setOnClickListener(this);
        Bibliographies.setOnClickListener(this);
        JournalArticle.setOnClickListener(this);
        Newsletter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;

        switch (v.getId()) {
            case R.id.ques_paper : i = new Intent(this,QPaperActivity.class);startActivity(i); break;
            case R.id.book_review : i =new Intent(this,BkReview.class);startActivity(i); break;
            case R.id.bibliographies : i =new Intent(this,Bibliography.class);startActivity(i); break;
            case R.id.jour_article : i =new Intent(this,Journal.class);startActivity(i); break;
            case R.id.newsletter : i =new Intent(this,Newsletter.class);startActivity(i); break;
            default:break;
        }
    }
}