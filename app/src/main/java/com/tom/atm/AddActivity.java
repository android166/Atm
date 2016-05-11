package com.tom.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddActivity extends AppCompatActivity {

    private EditText edDate;
    private EditText edInfo;
    private EditText edAmount;
    private MyDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findViews();
        helper = MyDBHelper.getInstance(this);
    }

    private void findViews() {
        edDate = (EditText)findViewById(R.id.ed_date);
        edInfo = (EditText)findViewById(R.id.ed_info);
        edAmount = (EditText)findViewById(R.id.ed_amount);
    }

    public void add(View v){
        String cdate = edDate.getText().toString();
        String info = edInfo.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());
        ExpenseDao.insert(new Expense(cdate, info, amount));
    }
}
