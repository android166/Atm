package com.tom.atm;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private EditText edUserid;
    private EditText edPasswd;
    private CheckBox cbUserid;
    private boolean rememberUserid;
    private String userid;
    private String passwd;
    private ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        String userid = getSharedPreferences("atm", MODE_PRIVATE)
                .getString("PREF_USERID", "");
        edUserid.setText(userid);
        cbUserid.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Log.d("CBOX", isChecked+"");
                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putBoolean("PREF_REMEMBER_USERID", isChecked)
                        .commit();
            }
        });
        rememberUserid = getSharedPreferences("atm", MODE_PRIVATE)
                .getBoolean("PREF_REMEMBER_USERID", false);
        cbUserid.setChecked(rememberUserid);

    }

    private void findViews() {
        edUserid = (EditText) findViewById(R.id.userid);
        edPasswd = (EditText) findViewById(R.id.passwd);
        cbUserid = (CheckBox) findViewById(R.id.remember_userid);
        pbar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void login(View v){
        userid = edUserid.getText().toString();
        passwd = edPasswd.getText().toString();
        String url = "http://atm201605.appspot.com/login?uid="+ userid +"&pw="+ passwd;
        new LoginTask().execute(url);
        /*
        if (userid.equals("jack") && passwd.equals("1234")){
            if (rememberUserid) {
                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putString("PREF_USERID", userid)
                        .commit();
            }
            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("USERID", userid);
            intent.putExtra("PASSWD", passwd);
            setResult(RESULT_OK, intent);

            finish();
        }
        */

    }

    class LoginTask extends AsyncTask<String, Void, Integer>{
        @Override
        protected void onPreExecute() {

            pbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... params) {
            int data = 0;
            try {
                URL url = new URL(params[0]);
                InputStream is = url.openStream();
                data = is.read();
                Thread.sleep(1000);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(Integer data) {
            pbar.setVisibility(View.GONE);
            if (data == 49){
                if (rememberUserid) {
                    getSharedPreferences("atm", MODE_PRIVATE)
                            .edit()
                            .putString("PREF_USERID", userid)
                            .commit();
                }
                Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("USERID", userid);
                intent.putExtra("PASSWD", passwd);
                setResult(RESULT_OK, intent);

                finish();
            }else{
                new AlertDialog.Builder(LoginActivity.this)
                        .setMessage("登入失敗")
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
    }
}
