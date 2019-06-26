package com.example.khulood.nmlibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.khulood.nmlibrary.Adapter.RVA;
import com.example.khulood.nmlibrary.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class Books_issued extends Navigation {

    ArrayList<BookIssued> book = new ArrayList<BookIssued>();
    SqliteHelper sqliteHelper;
    RecyclerView myrv;
    RVA myAdpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_books_issued, null, false);
        drawer.addView(contentView, 0);
        sqliteHelper = new SqliteHelper(this);
        final SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);
        final String sid = myPrefs.getString("sid", "null");

        book = sqliteHelper.issuehistory(sid);

        myrv = findViewById(R.id.rva);
        myAdpt = new RVA(this, book);
        myrv.setLayoutManager(new GridLayoutManager(this, 1));
        myrv.setAdapter(myAdpt);
        myAdpt.notifyDataSetChanged();
    }
}
