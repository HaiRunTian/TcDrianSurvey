package com.example.administrator.tcdrainsurvey.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.tcdrainsurvey.greendao.gen.DaoMaster;
import com.example.administrator.tcdrainsurvey.greendao.gen.DaoSession;

/**
 * @author HaiRun
 * @time 2019/4/18.10:39
 */

public class MyApplication extends Application {

    private DaoSession mDaoSession;
    public static MyApplication instances;
    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();

    }

    private void setDatabase() {
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this, "Drain.db", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        DaoMaster  mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static MyApplication getInstances(){
        return instances;
    }
}
