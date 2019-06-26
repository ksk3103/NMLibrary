package com.example.khulood.nmlibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

public class Journal extends Navigation implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_journal, null, false);
        drawer.addView(contentView, 0);
        init();
    }

    public void init() {
        CardView c1=(CardView)findViewById(R.id.j2014);
        CardView c2=(CardView)findViewById(R.id.j2013);
        CardView c3=(CardView)findViewById(R.id.j2012);
        CardView c4=(CardView)findViewById(R.id.j2011);
        CardView c5=(CardView)findViewById(R.id.j2010);
        CardView c6=(CardView)findViewById(R.id.j2009);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i; String url;
        switch (v.getId()) {
            case R.id.j2014 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOMEZweG1WTXg0Nzg/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.j2013 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOSEJXOFp1a3g1WGc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.j2012 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOcXk0alduVElqUUk/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.j2011 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOZU1peGI5N2NDbTA/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.j2010 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOZ1cxdzlwQTM4Vnc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.j2009 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOOFUyekpuUDF1cnc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            default: break;
        }
    }
}
