package com.example.khulood.nmlibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

public class Newsletter extends Navigation implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_newsletter, null, false);
        drawer.addView(contentView, 0);
        init();
    }

    public void init() {
        CardView c1 = (CardView)findViewById(R.id.jan19);
        CardView c2 = (CardView)findViewById(R.id.dec18);
        CardView c3 = (CardView)findViewById(R.id.nov18);
        CardView c4 = (CardView)findViewById(R.id.oct18);
        CardView c5 = (CardView)findViewById(R.id.sep18);
        CardView c6 = (CardView)findViewById(R.id.aug18);
        CardView c7 = (CardView)findViewById(R.id.jul18);
        CardView c8 = (CardView)findViewById(R.id.jun18);
        CardView c9 = (CardView)findViewById(R.id.may18);
        CardView c10 = (CardView)findViewById(R.id.apr18);
        CardView c11 = (CardView)findViewById(R.id.mar18);
        CardView c12 = (CardView)findViewById(R.id.feb18);
        CardView c13 = (CardView)findViewById(R.id.jan19);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
        c7.setOnClickListener(this);
        c8.setOnClickListener(this);
        c9.setOnClickListener(this);
        c10.setOnClickListener(this);
        c11.setOnClickListener(this);
        c12.setOnClickListener(this);
        c13.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i; String url;
        switch (v.getId()) {
            case R.id.jan19 : url = "https://drive.google.com/file/d/1uyrZpSaUG9Na0XopfqZz-AdpeqO2zo7_/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.dec18 : url = "https://drive.google.com/file/d/1HNkkG2N3QDooMJGgGNZL9h9yYA5SJSdC/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.nov18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOMFZ5b0R1RkFpamc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.oct18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXORDhDVklyc3Rfc2M/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.sep18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXORDhDVklyc3Rfc2M/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.aug18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXORDhDVklyc3Rfc2M/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.jul18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOZ0Z6RmR2eXFuNkU/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.jun18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOUThYMkdMQTBCa1k/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.may18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXObnZmWnl2a1ZMSWM/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.apr18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOak51R21Ya3JMcGc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.mar18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOYW91bjROQTk0aEE/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.feb18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOaTRVS1ZiaExVQXM/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.jan18 : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOOFp3RklRakF5QUU/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            default: break;
        }
    }

}
