package com.tom.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private static final int FUNC_LOGIN = 6;
    boolean logon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this, TestActivity.class));
        if (!logon){
            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
            startActivityForResult(intent, FUNC_LOGIN);
        }
        ListView list = (ListView) findViewById(R.id.list);
        String[] func = {"餘額查詢", "交易明細", "最新消息","投資理財", "離開"};
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, func);

        list.setAdapter(adapter);
        //Spinner
        Spinner notify = (Spinner) findViewById(R.id.spinner);
        /*
        String[] data = getResources().getStringArray(R.array.notify_array);
        ArrayAdapter adapter2 = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, data);
                */
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                R.array.notify_array, android.R.layout.simple_list_item_1);
        notify.setAdapter(adapter2);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_settings:
                return true;
            case R.id.action_exit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

