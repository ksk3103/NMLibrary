package com.example.khulood.nmlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.khulood.nmlibrary.Adapter.MyAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class Dashboard extends Navigation implements View.OnClickListener {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] BOOK= {R.drawable.access, R.drawable.search,R.drawable.scan,R.drawable.prince,R.drawable.network,R.drawable.ugc, R.drawable.pride, R.drawable.mutual};
    private ArrayList<Integer> BOOKArray = new ArrayList<Integer>();

    private CardView bookCard,inforepCard,jourCard,resCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_dashboard, null, false);
        drawer.addView(contentView, 0);
        init();

        //defining cards
        bookCard = (CardView) findViewById(R.id.books_card);
        inforepCard = (CardView) findViewById(R.id.inforep_card);
        jourCard = (CardView) findViewById(R.id.jour_card);
        resCard = (CardView) findViewById(R.id.res_card);
        // add click listener to the cards
        bookCard.setOnClickListener(this);
        inforepCard.setOnClickListener(this);
        jourCard.setOnClickListener(this);
        resCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;

        switch (v.getId()) {
            case R.id.books_card : i = new Intent(this,Books.class);startActivity(i); break;
            case R.id.inforep_card : i = new Intent(this,inforep.class);startActivity(i); break;
            case R.id.jour_card : i = new Intent(this,Magazines.class); startActivity(i); break;
            case R.id.res_card : i = new Intent(this,OnlineResources.class); startActivity(i); break;
            default:break;
        }
    }
    private void init() {
        for(int i=0;i<BOOK.length;i++)
            BOOKArray.add(BOOK[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(Dashboard.this, BOOKArray));
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(3*density);


        NUM_PAGES = BOOK.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

}

