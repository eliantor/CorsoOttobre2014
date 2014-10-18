package test.aktor.me.test.dynamic;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.Random;

/**
 * Created by eto on 10/18/14.
 */
public class NoViewFragment extends Fragment {

    private OnTextReady mListener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnTextReady)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf("TAG","WHT THE FUCK I' M INVISIBLE!!!!!");
        setRetainInstance(true); // MAGIC!!!!
        Log.wtf("TAG","ON MAIN");
    }

    public void startAsync() {
        RandomGenTask rgt = new RandomGenTask();
        rgt.execute(5000L);
    }

    public static interface OnTextReady {
        public void  onNewText(String text);
    }

    //     vvvvv
    public /*static*/ class RandomGenTask
            //                param prog  result
            extends AsyncTask<Long,Void,String>{

        @Override
        protected String doInBackground(Long... params) {
            Long timeToSleep = params[0];
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {

            }
            Random rand  = new Random();
            byte[] data = new byte[128];
            rand.nextBytes(data);
            String s = new String(data);
            return s;
        }

        @Override       ///          result
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("TAG","AFTER A WHILE: "+s);
            mListener.onNewText(s);
        }
    }

}
