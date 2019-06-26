
package com.example.khulood.nmlibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class ScanActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    BarcodeReader barcodeReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
    }

    @Override
    public void onScanned(Barcode barcode) {

        barcodeReader.playBeep();


        SharedPreferences myPrefs = getSharedPreferences("SharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("barcode", barcode.displayValue);
        editor.apply();

        finish();

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

        Toast.makeText(getApplicationContext(),"Error occured while scanning " + errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCameraPermissionDenied() {
        finish();
    }
}
