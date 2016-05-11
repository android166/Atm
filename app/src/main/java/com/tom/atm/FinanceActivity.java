package com.tom.atm;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class FinanceActivity extends AppCompatActivity {

    private ListView list;
    private MyDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        helper = MyDBHelper.getInstance(this);
        list = (ListView) findViewById(R.id.list);
        setupListView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(FinanceActivity.this, AddActivity.class));
            }
        });
    }

    private void setupListView() {
        Cursor c = helper.getReadableDatabase().query("exp",
                null, null, null, null, null, null);
        String[] from = {"_id", "cdate", "info", "amount"};
        int[] to = {R.id.item_id, R.id.item_cdate, R.id.item_info, R.id.item_amount};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.finance_row,
                c,
                from,
                to, 0){
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                super.bindView(view, context, cursor);
                int amount = cursor.getInt(cursor.getColumnIndex("amount"));
                if (amount>50){
                    TextView tv = (TextView) view.findViewById(R.id.item_amount);
                    tv.setTextColor(Color.RED);
                }
            }
        };
        list.setAdapter(adapter);
    }

}
