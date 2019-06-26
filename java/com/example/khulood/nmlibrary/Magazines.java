package com.example.khulood.nmlibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

import com.github.barteksc.pdfviewer.PDFView;

public class Magazines extends Navigation {
    private PDFView pdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_magazines, null, false);
        drawer.addView(contentView, 0);

        pdf = (PDFView)findViewById(R.id.magazine);
        pdf.fromAsset("magazine.pdf").load();
    }
}
