package com.example.deborah.pricechecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_TAG = "Products";
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // maak downloadertask aan
        ProductDownloadTask task = new ProductDownloadTask(this);

        // start task
        task.execute();
    }

    public void setQuote(ArrayList<String> quote) {
//        TextView tv = (TextView) findViewById(R.id.textView_products);
//        tv.setText(quote);

        lv = (ListView) findViewById(R.id.listView_1);


        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                quote);

        lv.setAdapter(arrayAdapter);
        Log.i("quote ", quote.toString());

        Intent i = getIntent();
        String eanNumbers = i.getStringExtra("eannumbers(scan):");
        //Log.i("eanNumbers(main) ", eanNumbers);

        if(quote.toString().contains("8710442420510")){
            Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
            //MainActivity.this.startActivity(new Intent(MainActivity.this, DetailProduct1Activity.class));
        } else {
            Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToScanActivity (View v) {
        MainActivity.this.startActivity(new Intent(MainActivity.this, ScanActivity.class));
    }
}
