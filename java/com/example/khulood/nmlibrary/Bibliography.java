package com.example.khulood.nmlibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

public class Bibliography extends Navigation implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_bibliography, null, false);
        drawer.addView(contentView, 0);
        init();
    }

    public void init() {
        CardView c1 = (CardView)findViewById(R.id.corporate);
        CardView c2 = (CardView)findViewById(R.id.child);
        CardView c3 = (CardView)findViewById(R.id.chanakya);
        CardView c4 = (CardView)findViewById(R.id.buddhism);
        CardView c5 = (CardView)findViewById(R.id.banking);
        CardView c6 = (CardView)findViewById(R.id.disaster);
        CardView c7 = (CardView)findViewById(R.id.food);
        CardView c8 = (CardView)findViewById(R.id.poverty);
        CardView c9 = (CardView)findViewById(R.id.stress);
        CardView c10 = (CardView)findViewById(R.id.waste);
        CardView c11 = (CardView)findViewById(R.id.knowledge);
        CardView c12 = (CardView)findViewById(R.id.happiness);
        CardView c13 = (CardView)findViewById(R.id.green);
        CardView c14 = (CardView)findViewById(R.id.innovation);
        CardView c15 = (CardView)findViewById(R.id.top);
        CardView c16 = (CardView)findViewById(R.id.soft);
        CardView c17 = (CardView)findViewById(R.id.gst);

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
        c14.setOnClickListener(this);
        c15.setOnClickListener(this);
        c16.setOnClickListener(this);
        c17.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i; String url;
        switch (v.getId()) {
            case R.id.corporate : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOR0lCaFAyYWhFR0k/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.child : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOTElydWl4MWxtVXc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.chanakya : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOY0VKYXNuM1IxSzA/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.buddhism : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOSEhUYndrRWY2Qmc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.banking : url = "https://drive.google.com/file/d/0B1_zV6bDJrXORlBFQVVIUE1FQWM/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.waste : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOSWFuVXJMQktCbWM/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.knowledge : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOcjhjOG93RUNYaHM/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.food : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOWGhQdnMxWXVWRHM/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.poverty : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOd2Ntc1Rza3hobHc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.stress : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOTEJBOTBKUlZBbXc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.happiness : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOQkowS3FmMmtiaFk/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.gst : url = "https://drive.google.com/file/d/1YO0aKvqmTo35RrwPcuFTGP10j4p8N5E9/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.soft : url = "https://drive.google.com/file/d/1lMdJpccBEwuZGjVzqQkhAhWKUCXXeh-g/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.top : url = "https://drive.google.com/file/d/1SyXp1Y20hApt5tHqpIwhwwulRbVtdqKi/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.green : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOZU1XTHpaZ3Y4ZVk/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.innovation : url = "https://drive.google.com/file/d/10Bomm-Vll3gHk5LBCK3H75UJrZoxKrsA/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.disaster : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOMUctRXdET01ScjQ/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            default: break;
        }
    }
}
