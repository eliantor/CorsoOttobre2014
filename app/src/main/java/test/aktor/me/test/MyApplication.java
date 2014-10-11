package test.aktor.me.test;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import test.aktor.me.test.lists.AndroidStudent;

/**
 * Created by eto on 11/10/14.
 */
public class MyApplication extends Application {

    private List<AndroidStudent> database;

    private static MyApplication sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        database = new ArrayList<AndroidStudent>();

        sApp = this;
    }


    public static MyApplication get(){
        return sApp;
    }

    public List<AndroidStudent> getStudents(){
        return database;
    }

}
