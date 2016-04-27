package com.tom.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText edUserid;
    private EditText edPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        String userid = getSharedPreferences("atm", MODE_PRIVATE)
                .getString("PREF_USERID", "");
        edUserid.setText(userid);

    }

    private void findViews() {
        edUserid = (EditText) findViewById(R.id.userid);
        edPasswd = (EditText) findViewById(R.id.passwd);
    }

    public void login(View v){
        String userid = edUserid.getText().toString();
        String passwd = edPasswd.getText().toString();
        if (userid.equals("jack") && passwd.equals("1234")){
            getSharedPreferences("atm", MODE_PRIVATE)
                    .edit()
                    .putString("PREF_USERID", userid)
                    .commit();

            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("USERID", userid);
            intent.putExtra("PASSWD", passwd);
            setResult(RESULT_OK, intent);

            finish();
        }

    }
}
