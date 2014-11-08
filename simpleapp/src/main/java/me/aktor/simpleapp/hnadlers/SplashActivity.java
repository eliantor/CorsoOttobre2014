package me.aktor.simpleapp.hnadlers;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;

/**
 * Created by Andrea Tortorella on 11/8/14.
 */
public class SplashActivity extends Activity {

    public static final int ACTION_START_ACTIVITY = 40;
private final Runnable action = new Runnable() {
    @Override
    public void run() {

    }
};
    private MyHandler mHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new MyHandler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        mHandler.postDelayed(action,1000);
        mHandler.sendEmptyMessage(ACTION_START_ACTIVITY);
        Message message = mHandler.obtainMessage(ACTION_START_ACTIVITY, 0, 0, this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(action);
        mHandler.removeMessages(ACTION_START_ACTIVITY);
    }

    private static class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==ACTION_START_ACTIVITY){
                /// s

            }
            super.handleMessage(msg);

        }
    }
}
