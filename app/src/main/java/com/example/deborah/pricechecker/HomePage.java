package com.example.deborah.pricechecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

/**
 * Created by Deborah on 4/10/2017.
 */

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        PulsatorLayout pulsator = (PulsatorLayout) findViewById(R.id.pulsator);
        pulsator.start();

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent homePage = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(homePage);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
