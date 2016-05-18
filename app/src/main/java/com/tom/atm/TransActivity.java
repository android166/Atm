package com.tom.atm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

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
            try {
                JSONArray array = new JSONArray(s);
                ArrayList<HashMap<String, String>> data =
                        new ArrayList<>();
                for (int i=0; i<array.length(); i++){
                    JSONObject obj = array.getJSONObject(i);
                    String account = obj.getString("account");
                    String date = obj.getString("date");
                    int amount = obj.getInt("amount");
                    int type = obj.getInt("type");
                    Log.d("OBJ", account+"/"+date+"/"+amount+"/"+type);
                    HashMap<String, String> row = new HashMap<>();
                    row.put("account", account);
                    row.put("date", date);
                    row.put("amount", amount+"");
                    row.put("type", type+"");
                    data.add(row);
                }
                ListView list = (ListView) findViewById(R.id.listView);
                String[] from = {"account", "date", "amount", "type"};
                int[] to = {R.id.row_account, R.id.row_date, R.id.row_amount, R.id.row_type};
                SimpleAdapter adapter = new SimpleAdapter(TransActivity.this,
                        data,
                        R.layout.trans_row, from , to );
                list.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
