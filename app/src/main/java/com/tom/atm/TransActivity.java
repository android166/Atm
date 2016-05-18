package com.tom.atm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TransActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        String url = "http://atm201605.appspot.com/h";
        new TransTask().execute(url);
    }
    class TransTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
//            String data = "";
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(params[0]);
             /*   InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader in = new BufferedReader(isr);*/
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream()));
                String line = in.readLine();
                while(line!=null){
//                    data = data + line;
                    sb.append(line);
                    line = in.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("Trans", sb.toString());
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
}