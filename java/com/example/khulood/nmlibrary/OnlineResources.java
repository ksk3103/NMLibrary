package com.example.khulood.nmlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class OnlineResources extends Navigation implements View.OnClickListener {
    WebView webView; LinearLayout l1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_online_resources, null, false);
        drawer.addView(contentView, 0);
        l1 = (LinearLayout) findViewById(R.id.online);
        webView = (WebView) findViewById (R.id.webview);
        init();
    }

    public void init() {
        CardView c1 = (CardView)findViewById(R.id.crd1);
        CardView c2 = (CardView)findViewById(R.id.crd2);
        CardView c3 = (CardView)findViewById(R.id.crd3);
        CardView c4 = (CardView)findViewById(R.id.crd4);
        CardView c5 = (CardView)findViewById(R.id.crd5);
        CardView c6 = (CardView)findViewById(R.id.crd6);
        CardView c7 = (CardView)findViewById(R.id.crd7);
        CardView c8 = (CardView)findViewById(R.id.crd8);
        CardView c9 = (CardView)findViewById(R.id.crd9);
        CardView c10 = (CardView)findViewById(R.id.crd10);
        CardView c11 = (CardView)findViewById(R.id.crd11);
        CardView c12 = (CardView)findViewById(R.id.crd12);
        CardView c13 = (CardView)findViewById(R.id.crd13);
        CardView c14 = (CardView)findViewById(R.id.crd14);
        CardView c15 = (CardView)findViewById(R.id.crd15);
        CardView c16 = (CardView)findViewById(R.id.crd16);
        CardView c17 = (CardView)findViewById(R.id.crd17);
        CardView c18 = (CardView)findViewById(R.id.crd18);
        CardView c19 = (CardView)findViewById(R.id.crd19);
        CardView c20 = (CardView)findViewById(R.id.crd20);
        CardView c21 = (CardView)findViewById(R.id.crd21);
        CardView c22 = (CardView)findViewById(R.id.crd22);
        CardView c23 = (CardView)findViewById(R.id.crd23);
        CardView c24 = (CardView)findViewById(R.id.crd24);
        CardView c25 = (CardView)findViewById(R.id.crd25);
        CardView c26 = (CardView)findViewById(R.id.crd26);
        CardView c27 = (CardView)findViewById(R.id.crd27);
        CardView c28 = (CardView)findViewById(R.id.crd28);

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
        c12.setOnClickListener(this);
        c13.setOnClickListener(this);
        c14.setOnClickListener(this);
        c16.setOnClickListener(this);
        c17.setOnClickListener(this);
        c18.setOnClickListener(this);
        c19.setOnClickListener(this);
        c20.setOnClickListener(this);
        c21.setOnClickListener(this);
        c22.setOnClickListener(this);
        c23.setOnClickListener(this);
        c24.setOnClickListener(this);
        c25.setOnClickListener(this);
        c26.setOnClickListener(this);
        c27.setOnClickListener(this);
        c28.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        webView.setVisibility(View.VISIBLE);
        getSupportActionBar().hide();
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.reload();

        switch (v.getId()){
            case R.id.crd1 : webView.loadUrl("https://www.britishcouncil.in/library"); break;
            case R.id.crd2 : webView.loadUrl("https://indcat.inflibnet.ac.in/index.php/main/theses"); break;
            case R.id.crd3 : webView.loadUrl("http://www.ndltd.org/"); break;
            case R.id.crd4 : webView.loadUrl("http://shodhganga.inflibnet.ac.in/"); break;
            case R.id.crd5 : webView.loadUrl("http://etheses.saurashtrauniversity.edu/"); break;
            case R.id.crd6 : webView.loadUrl("https://swayam.gov.in"); break;
            case R.id.crd7 : webView.loadUrl("http://epgp.inflibnet.ac.in"); break;
            case R.id.crd8 : webView.loadUrl("https://ndl.iitkgp.ac.in/"); break;
            case R.id.crd9 : webView.loadUrl("https://archive.org/details/universallibrary "); break;
            case R.id.crd10 : webView.loadUrl("http://www.censusindia.gov.in/DigitalLibrary/Archive_home.aspx"); break;
            case R.id.crd11 : webView.loadUrl("https://archive.org/details/millionbooks"); break;
            case R.id.crd12 : webView.loadUrl("https://archive.org/details/digitallibraryindia"); break;
            case R.id.crd13 : webView.loadUrl("https://www.indiabudget.gov.in/"); break;
            case R.id.crd14 : webView.loadUrl("https://archive.org/details/universallibrary "); break;
            case R.id.crd15 : webView.loadUrl("https://bulletin.rbi.org.in/"); break;
            case R.id.crd16 : webView.loadUrl("http://dgft.gov.in"); break;
            case R.id.crd17 : webView.loadUrl("http://www.exim-policy.com"); break;
            case R.id.crd18 : webView.loadUrl("http://upscfever.com/upsc-fever/index.html"); break;
            case R.id.crd19 : webView.loadUrl("https://www.startupindia.gov.in"); break;
            case R.id.crd20 : webView.loadUrl("https://www.mooc-list.com/"); break;
            case R.id.crd21 : webView.loadUrl("https://www.udemy.com/"); break;
            case R.id.crd22 : webView.loadUrl("https://news.stanford.edu/2018/06/22/moocs"); break;
            case R.id.crd23 : webView.loadUrl("http://webcast.berkeley.edu"); break;
            case R.id.crd24 : webView.loadUrl("https://ocw.mit.edu/index.htm"); break;
            case R.id.crd25 : webView.loadUrl("https://dictionary.cambridge.org/dictionary/british/"); break;
            case R.id.crd26 : webView.loadUrl("https://www.merriam-webster.com"); break;
            case R.id.crd27 : webView.loadUrl("https://www.oxfordlearnersdictionaries.com"); break;
            case R.id.crd28 : webView.loadUrl("https://www.bartleby.com/110"); break;
            default: break;

        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else if (webView.getVisibility() == View.VISIBLE) {
            webView.clearCache(true);
            webView.clearHistory();
            webView.clearView();
            webView.setVisibility(View.GONE);
            l1.setVisibility(View.VISIBLE);
            getSupportActionBar().show();
        }
        else {
            super.onBackPressed();
        }
    }

}
