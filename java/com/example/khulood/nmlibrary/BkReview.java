package com.example.khulood.nmlibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class BkReview extends Navigation implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_bk_review, null, false);
        drawer.addView(contentView, 0);
        init();

    }

    public void init() {
        CardView apr = (CardView)findViewById(R.id.april);
        CardView mar = (CardView)findViewById(R.id.march);
        CardView feb = (CardView)findViewById(R.id.february);
        CardView jan = (CardView)findViewById(R.id.january);
        CardView dec = (CardView)findViewById(R.id.december);
        CardView nov = (CardView)findViewById(R.id.november);
        CardView oct = (CardView)findViewById(R.id.october);
        CardView sep = (CardView)findViewById(R.id.september);
        CardView aug = (CardView)findViewById(R.id.august);
        CardView jul = (CardView)findViewById(R.id.july);

        apr.setOnClickListener(this);
        mar.setOnClickListener(this);
        feb.setOnClickListener(this);
        jan.setOnClickListener(this);
        dec.setOnClickListener(this);
        nov.setOnClickListener(this);
        oct.setOnClickListener(this);
        sep.setOnClickListener(this);
        aug.setOnClickListener(this);
        jul.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i; String url;
        switch (v.getId()) {
            case R.id.april : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOb1VzU3BPeVJ3WG8/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.march : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOLUVubkpENHo0Qkk/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.february : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOd3VTOC1qMmM2SUU/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.january : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOTXJHOFVvRldJUWc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.december : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOWDVneDEtcFpTT2c/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.november : url = "https://drive.google.com/file/d/0B1_zV6bDJrXONnc4a3BuZHZZWW8/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.october : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOVkhBU0xSM3NMdFE/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.september : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOZ0ZLTENWR05OcE0/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.august : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOWFNhaHVjN0kweG8/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            case R.id.july : url = "https://drive.google.com/file/d/0B1_zV6bDJrXOX3dsb1NJRDRxMXc/view"; i = new Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url)); startActivity(i); break;
            default: break;
        }
    }
}
