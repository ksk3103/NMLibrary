package com.example.khulood.nmlibrary;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.khulood.nmlibrary.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

public class DisplayBook extends AppCompatActivity implements RecyclerViewAdapter.BookListener {
    View contentView;
    ArrayList<book> lstbook = new ArrayList<book>();
    RecyclerView myrv;
    RecyclerViewAdapter myAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private SearchView searchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_book);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        final Intent i = getIntent();
        lstbook = (ArrayList<book>) i.getSerializableExtra("book");

        myrv = findViewById(R.id.recyclerview_id);
        swipeRefreshLayout = findViewById(R.id.swipe);
        myrv.removeAllViews();
        myAdapter = new RecyclerViewAdapter(this, lstbook, swipeRefreshLayout, this);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));
        myrv.setItemAnimator(new DefaultItemAnimator());
        myrv.setAdapter(myAdapter);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myAdapter.notifyDataSetChanged();
                myrv.invalidate();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchwidget, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                myAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                myAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }


    @Override
    public void onResume() {
        myAdapter.notifyDataSetChanged();
        myrv.invalidate();
        super.onResume();
    }

    @Override
    public void onBookSelected(book Book) {

        Toast.makeText(getApplicationContext(), "Selected: " + Book.getTitle() + ", " + Book.getAuthor() + ", " + Book.getPublisher() + ", " + Book.getPublish_date() + ", " + Book.getISBN(), Toast.LENGTH_LONG).show();

    }
}
