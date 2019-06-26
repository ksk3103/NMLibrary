package com.example.khulood.nmlibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class QPaperActivity extends Navigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_qpaper, null, false);
        drawer.addView(contentView, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initviews();

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //Tab1
        TabHost.TabSpec spec = host.newTabSpec("BCOM");
        spec.setContent(R.id.bcom);
        spec.setIndicator("Bcom");
        host.addTab(spec);

        //Tab2
        spec = host.newTabSpec("BFM");
        spec.setContent(R.id.bfm);
        spec.setIndicator("BFM");
        host.addTab(spec);

        //Tab3
        spec = host.newTabSpec("BMS");
        spec.setContent(R.id.bms);
        spec.setIndicator("BMS");
        host.addTab(spec);

        //Tab4
        spec = host.newTabSpec("BAF");
        spec.setContent(R.id.baf);
        spec.setIndicator("BAF");
        host.addTab(spec);

        //Tab5
        spec = host.newTabSpec("BSCIT");
        spec.setContent(R.id.bscit);
        spec.setIndicator("BSCIT");
        host.addTab(spec);
    }


    private void initviews() {
        TextView txt1 = (TextView) findViewById(R.id.textview9);
        TextView txt2 = (TextView) findViewById(R.id.textview10);
        TextView txt3 = (TextView) findViewById(R.id.textView14);
        TextView txt4 = (TextView) findViewById(R.id.textView16);
        TextView txt5 = (TextView) findViewById(R.id.textView19);
        TextView txt6 = (TextView) findViewById(R.id.textview24);
        TextView txt7 = (TextView) findViewById(R.id.textView26);
        TextView txt8 = (TextView) findViewById(R.id.textView29);
        TextView txt9 = (TextView) findViewById(R.id.textView30);
        TextView txt10 = (TextView) findViewById(R.id.textView34);
        TextView txt11 = (TextView) findViewById(R.id.textView35);
        TextView txt12 = (TextView) findViewById(R.id.textView20);
        TextView txt13 = (TextView) findViewById(R.id.textView44);
        TextView txt14 = (TextView) findViewById(R.id.textView45);
        TextView txt15 = (TextView) findViewById(R.id.textView47);
        TextView txt16 = (TextView) findViewById(R.id.textview48);
        TextView txt17 = (TextView) findViewById(R.id.textView50);
        TextView txt18 = (TextView) findViewById(R.id.textView51);
        TextView txt19 = (TextView) findViewById(R.id.textView10);
        TextView txt20 = (TextView) findViewById(R.id.textView11);
        TextView txt21 = (TextView) findViewById(R.id.textView39);
        TextView txt22 = (TextView) findViewById(R.id.textview40);
        TextView txt23 = (TextView) findViewById(R.id.textView41);
        TextView txt24 = (TextView) findViewById(R.id.textView42);
        TextView txt25 = (TextView) findViewById(R.id.textView53);
        TextView txt26 = (TextView) findViewById(R.id.textView54);
        TextView txt27 = (TextView) findViewById(R.id.textView56);
        TextView txt28 = (TextView) findViewById(R.id.textview57);
        TextView txt29 = (TextView) findViewById(R.id.textView59);
        TextView txt30 = (TextView) findViewById(R.id.textView60);
        txt1.setMovementMethod(LinkMovementMethod.getInstance());
        txt2.setMovementMethod(LinkMovementMethod.getInstance());
        txt3.setMovementMethod(LinkMovementMethod.getInstance());
        txt4.setMovementMethod(LinkMovementMethod.getInstance());
        txt5.setMovementMethod(LinkMovementMethod.getInstance());
        txt6.setMovementMethod(LinkMovementMethod.getInstance());
        txt7.setMovementMethod(LinkMovementMethod.getInstance());
        txt8.setMovementMethod(LinkMovementMethod.getInstance());
        txt9.setMovementMethod(LinkMovementMethod.getInstance());
        txt10.setMovementMethod(LinkMovementMethod.getInstance());
        txt11.setMovementMethod(LinkMovementMethod.getInstance());
        txt12.setMovementMethod(LinkMovementMethod.getInstance());
        txt13.setMovementMethod(LinkMovementMethod.getInstance());
        txt14.setMovementMethod(LinkMovementMethod.getInstance());
        txt15.setMovementMethod(LinkMovementMethod.getInstance());
        txt16.setMovementMethod(LinkMovementMethod.getInstance());
        txt17.setMovementMethod(LinkMovementMethod.getInstance());
        txt18.setMovementMethod(LinkMovementMethod.getInstance());
        txt19.setMovementMethod(LinkMovementMethod.getInstance());
        txt20.setMovementMethod(LinkMovementMethod.getInstance());
        txt21.setMovementMethod(LinkMovementMethod.getInstance());
        txt22.setMovementMethod(LinkMovementMethod.getInstance());
        txt23.setMovementMethod(LinkMovementMethod.getInstance());
        txt24.setMovementMethod(LinkMovementMethod.getInstance());
        txt25.setMovementMethod(LinkMovementMethod.getInstance());
        txt26.setMovementMethod(LinkMovementMethod.getInstance());
        txt27.setMovementMethod(LinkMovementMethod.getInstance());
        txt28.setMovementMethod(LinkMovementMethod.getInstance());
        txt29.setMovementMethod(LinkMovementMethod.getInstance());
        txt30.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
