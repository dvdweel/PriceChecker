package com.example.deborah.pricechecker;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener {

    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    ArrayList<String> eanNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);

        scanBtn.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
            Log.i("content ", scanContent);
            eanNumbers.add(scanContent);
            Log.i("ean numbers(scan) ", String.valueOf(eanNumbers));

            if (eanNumbers.contains("9781612620268")) {
                Intent i = new Intent(this, DetailProduct1Activity.class);
                i.putExtra("eannumbers(detailpage):", eanNumbers.toString());
                startActivity(i);
                eanNumbers.clear();
            } else if (eanNumbers.contains("602547161697")){
                Intent i = new Intent(this, DetailProduct2Activity.class);
                i.putExtra("eannumbers(detailpage):", eanNumbers.toString());
                startActivity(i);
                eanNumbers.clear();
            }
                else {
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("eannumbers(scan):", eanNumbers.toString());
                startActivity(i);
                eanNumbers.clear();
            }

        } else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
