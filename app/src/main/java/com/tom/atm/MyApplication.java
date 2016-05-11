package com.tom.atm;

import android.app.Application;

/**
 * Created by Administrator on 2016/5/11.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ExpenseDao.context = getApplicationContext();
    }
}
