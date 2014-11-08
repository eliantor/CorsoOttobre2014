package me.aktor.simpleapp.strategies;

import android.support.v4.app.FragmentActivity;

/**
 * Created by Andrea Tortorella on 11/8/14.
 */
public class StrategicActivity extends FragmentActivity implements
    ShowDetailStrategy, ShowDetailStrategy.Setter{

    private ShowDetailStrategy strategy;

    @Override
    public void showDetail(long id) {
        if (strategy != null){
            strategy.showDetail(id);
        } else {
            // default
        }
    }

    @Override
    public void strategy(ShowDetailStrategy s) {

    }
}
