package me.aktor.simpleapp.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import me.aktor.simpleapp.R;
import me.aktor.utils.MagicMethods;

/**
 * Created by Andrea Tortorella on 10/25/14.
 */
public class MainActivity extends ActionBarActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MagicMethods.showTheRabbit();
    }
}
