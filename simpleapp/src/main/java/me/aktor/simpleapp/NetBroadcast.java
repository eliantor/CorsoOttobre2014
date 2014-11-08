package me.aktor.simpleapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by Andrea Tortorella on 11/8/14.
 */
public class NetBroadcast extends WakefulBroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())){
            ConnectivityManager m =(ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = m.getActiveNetworkInfo();
            if(ConnectivityManager.TYPE_WIFI == activeNetworkInfo.getType()) {
                // fail 'upload
                NetService.start(context);
                startWakefulService(context,new Intent());
            }
        }
    }
}
