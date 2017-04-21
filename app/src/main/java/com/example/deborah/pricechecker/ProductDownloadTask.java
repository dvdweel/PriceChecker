package com.example.deborah.pricechecker;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Deborah on 4/11/2017.
 */

public class ProductDownloadTask extends AsyncTask<String, Void, ArrayList<String>> {
    ArrayList<String> list = new ArrayList<>();

    private WeakReference<MainActivity> mainActivityWeakReference;

    public ProductDownloadTask(MainActivity mainActivity) {
        // bewaar referentie naar main om resultaat terug te sturen
        mainActivityWeakReference = new WeakReference<MainActivity>(mainActivity);
    }

    // wordt aangeroepen door Android op de achtergrond nadat je de AsyncTask met 'execute()' uitvoert
    @Override
    protected ArrayList<String> doInBackground(String... params) {
        // Haal informatie van internet
        String name = "UNDEFINED";
        String description = "UNDEFINED";
        String price = "UNDEFINED";
        String ean = "UNDEFINED";
        String results = "UNDEFINED";

        // default text

        HttpURLConnection con = null;
        Log.d(MainActivity.LOG_TAG, "Getting a joke");

        try {
            // Build RESTful query for icndb

            URL url = new URL(
                    "https://api.myjson.com/bins/yj81j");
            con = (HttpURLConnection) url.openConnection();

            con.setReadTimeout(10000 /* milliseconds */);
            con.setConnectTimeout(15000 /* milliseconds */);
            con.setRequestMethod("GET");

            con.setDoInput(true);

            // Start the query
            con.connect();

            // Read results from the query
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "UTF-8"));
            String payload = reader.readLine();
            reader.close();

            JSONObject topLevel = new JSONObject(payload.toString());
            JSONArray main = topLevel.getJSONArray("products");
            Log.i("main", main.toString());
            for (int i = 0; i < main.length(); i++) {
                JSONObject main2 = main.getJSONObject(i);
                    name = String.valueOf(main2.getString("name"));
                    description = String.valueOf(main2.getString("description"));
                    price = String.valueOf(main2.getString("price"));
                    ean = String.valueOf(main2.getString("ean"));

                    list.add("PRODUCT: " + name + " \r\n" + "DESCRIPTION: " + description + " \r\n" + "EAN: " + ean + " \r\n" + "PRICE: " + price);
            }

        } catch (IOException e) {
            Log.e(MainActivity.LOG_TAG, "IOException", e);
        } catch (JSONException e) {
            Log.e(MainActivity.LOG_TAG, "JSONException", e);
        } catch (Exception e) {
            Log.d(MainActivity.LOG_TAG, "Something went wrong... ", e);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return list;
                //.toString().replace("[", "").replace("]", "").replaceAll(",", "");

    }

    // wordt aangeroepen door Android als doInBackground klaar is en uitgevoerd op de UI-thread
    @Override
    protected void onPostExecute(ArrayList<String> s) {
        // stuur resultaat naar de main activity

        mainActivityWeakReference.get().setQuote(s);

    }
}
