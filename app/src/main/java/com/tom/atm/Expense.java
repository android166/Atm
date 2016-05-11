package com.tom.atm;

import android.content.ContentValues;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/11.
 */
public class Expense {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int id;
    Date date;
    String info;
    int amount;
    public Expense(String d, String info, int amount) {
        try {
            date = sdf.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public Expense(int id, Date date, String info, int amount) {
        this.id = id;
        this.date = date;
        this.info = info;
        this.amount = amount;
    }

    public Expense(Date date, String info, int amount) {
        this.date = date;
        this.info = info;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();
        values.put(ExpenseDao.COLUMN_DATE, sdf.format(date));
        values.put(ExpenseDao.COLUMN_INFO, info);
        values.put(ExpenseDao.COLUMN_AMOUNT, amount);
        return values;
    }

}
