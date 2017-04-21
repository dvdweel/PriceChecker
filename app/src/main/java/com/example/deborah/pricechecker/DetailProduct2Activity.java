package com.example.deborah.pricechecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Deborah on 4/18/2017.
 */

public class DetailProduct2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpage2);

//        Intent i = getIntent();
//        String eanNumbers = i.getStringExtra("eannumbers(detailpage):");
//        Log.i("ean numbers(details) ", String.valueOf(eanNumbers));
//
//
//        TextView tv = (TextView) findViewById(R.id.textView_ean);
//        tv.setText(eanNumbers);

        TextView amazonLink = (TextView) findViewById(R.id.textView_linkebayimagine);
        if (amazonLink != null) {
            amazonLink.setMovementMethod(LinkMovementMethod.getInstance());
        }

    }
}
