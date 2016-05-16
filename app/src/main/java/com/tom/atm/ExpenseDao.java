package com.tom.atm;

import android.content.Context;

/**
 * Created by Administrator on 2016/5/11.
 */
public class ExpenseDao {
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "cdate";
    public static final String COLUMN_INFO = "info";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String TABLE_NAME = "exp";

    public static Context context;
    static MyDBHelper helper;
    public static void initialize(Context context){
        helper = MyDBHelper.getInstance(context);
        ExpenseDao.context = context;
    }
    public static void insert(Expense expense) {
        helper.getWritableDatabase().insert(ExpenseDao.TABLE_NAME, null, expense.getContentValues());
    }
}
