package com.tom.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final int FUNC_LOGIN = 6;
    boolean logon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!logon){
            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
            startActivityForResult(intent, FUNC_LOGIN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case FUNC_LOGIN:
                if (resultCode == RESULT_OK){
                    String userid = data.getStringExtra("USERID");
                    String passwd = data.getStringExtra("PASSWD");
                    Log.d("LOGIN:", userid + "/" + passwd);
                }
                break;
        }
    }
}

