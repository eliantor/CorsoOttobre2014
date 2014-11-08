package me.aktor.simpleapp;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import me.aktor.simpleapp.ui.data.Contract;

/**
 * Created by Andrea Tortorella on 11/8/14.
 */
public class NetService extends IntentService {

    private static final String FORCED = "forced";

    public static void forceStart(Context context){
        Intent intent = new Intent(context,NetService.class);
        intent.putExtra(FORCED,true);
        context.startService(intent);
    }


    public static void start(Context context){
        Intent intent = new Intent(context,NetService.class);
        context.startService(intent);
    }

    public NetService() {
        super(NetService.class.getName());
    }

    private OkHttpClient mClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mClient = new OkHttpClient();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ContentResolver r = getContentResolver();
        Cursor c = null;
        try {
            c = r.query(Contract.Note.CONTENT_URI, null,
                    Contract.Note.SYNC_STATUS + " = " + Contract.SyncStatuses.NOT_SYNCED,
                    null, null
            );
            int action = verifyPolicy(intent,c);
            switch (action) {
                case CONTINUE:
                    cancelPending();
                    int idIndx = c.getColumnIndex(Contract.Note._ID);

                    while (c.moveToNext()) {
                        long id = c.getLong(idIndx);
                        setSaving(r, id);
                        try {
                            save(c);
                            setSaved(r, id);
                        } catch (IOException e) {
                            // failure
                        }
                    }
                    break;
                case ABORT:
                    break;
                case DELAY:
                    delay();
                    break;
            }

            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        } finally {
            if (c!=null && !c.isClosed()){
                c.close();
            }
        }
    // thread in background

    }

    private void cancelPending(){
        Intent intent = new Intent(this,NetService.class);
        intent.putExtra(FORCED,true);
        PendingIntent pi = PendingIntent.getService(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        AlarmManager alarm  = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.cancel(pi);
    }

    private void delay(){
        Intent intent = new Intent(this,NetService.class);
        intent.putExtra(FORCED,true);
        PendingIntent pi = PendingIntent.getService(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        AlarmManager alarm  = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.set(AlarmManager.ELAPSED_REALTIME,
                TimeUnit.MINUTES.toMillis(10),pi);

    }

    private static final int CONTINUE = 0;
    private static final int ABORT = 1;
    private static final int DELAY = 2;

    private int verifyPolicy(Intent intent, Cursor c) {
        if (intent.getBooleanExtra(FORCED,false)){
            return CONTINUE;
        } else {
            if (c.getCount()>=10){
                return CONTINUE;
            } else {
                return DELAY;
            }
        }
    }

    private void save(Cursor c) throws IOException {
        String json = fromCursor(c);
        Request request = new Request.Builder()
                .url("http://localhost:9000/docuemnts/notes")
                .post(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        json))
                .build();
        Response response = mClient.newCall(request).execute();
        if (response.code()/100 == 2){
            String string = response.body().string();

        }

    }

    private String fromCursor(Cursor c) {
        return null;
    }

    private void setSaved(ContentResolver r, long id) {
        setStatus(r,id,Contract.SyncStatuses.SYNCED);
    }

    private void setSaving(ContentResolver r, long id) {
        setStatus(r,id,Contract.SyncStatuses.SYNCING);
    }

    private void setStatus(ContentResolver r,long id,int status){
        ContentValues values = new ContentValues();
        values.put(Contract.Note.SYNC_STATUS, status);
        r.update(Contract.Note.getNoteUri(id),values,null,null);
    }


}
