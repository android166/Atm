package com.tom.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    private static final int FUNC_LOGIN = 6;
    boolean logon = false;
    String[] func = {"餘額查詢", "交易明細", "最新消息","投資理財", "離開"};
    int icons[] = {R.drawable.func_balance,
        R.drawable.func_history,
        R.drawable.func_news,
        R.drawable.func_finance,
        R.drawable.func_exit};
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
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, func);

        list.setAdapter(adapter);
//        list.setOnItemClickListener(this);
        //Spinner
        Spinner notify = (Spinner) findViewById(R.id.spinner);
        /*
        String[] data = getResources().getStringArray(R.array.notify_array);
        ArrayAdapter adapter2 = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, data);
                */
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                R.array.notify_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        notify.setAdapter(adapter2);
        notify.setOnItemSelectedListener(this);
        // gridview
        GridView grid = (GridView)findViewById(R.id.gridView);
        IconAdapter iconAdapter = new IconAdapter();
        grid.setAdapter(iconAdapter);
        grid.setOnItemClickListener(this);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] data = getResources().getStringArray(R.array.notify_array);
        Log.d("SPIN", position+"/"+data[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long itemId) {
        switch((int)itemId){
            case R.drawable.func_balance: //餘額查詢
                break;
            case R.drawable.func_history: //交易明細
                break;
            case R.drawable.func_news:
                break;
            case R.drawable.func_finance:
                break;
            case R.drawable.func_exit:
                finish();
                break;

        }


        /*
        int viewId = parent.getId();
        switch(viewId){
            case R.id.list:
                break;

            case R.id.gridView:
                break;
        }
        */
    }
    class IconAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            Log.d("ICON", "getCount");
            return icons.length;
        }

        @Override
        public Object getItem(int position) {
            Log.d("ICON", "getItem");
            return func[position];
        }

        @Override
        public long getItemId(int position) {
            Log.d("ICON", "getItemId");
//            return position;
            return icons[position];
        }

        @Override
        public View getView(int position,
                            View convertView, ViewGroup parent) {
            Log.d("ICON", "getView");
            View view = convertView;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.icon, null);
                TextView tv = (TextView) view.findViewById(R.id.icon_text);
                ImageView iv = (ImageView) view.findViewById(R.id.icon_image);
                tv.setText(func[position]);
                iv.setImageResource(icons[position]);
                convertView = view;
            }
            return convertView;
        }
    }
}

