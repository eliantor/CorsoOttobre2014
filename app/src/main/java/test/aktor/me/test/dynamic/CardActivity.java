package test.aktor.me.test.dynamic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import test.aktor.me.test.R;

/**
 * Created by eto on 10/18/14.
 */
public class CardActivity extends FragmentActivity
        implements View.OnClickListener,
        NoViewFragment.OnTextReady{

    private static final String TAG = "NO_VIEW";

    private TextView mOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        findViewById(R.id.bt_red).setOnClickListener(this);
        findViewById(R.id.bt_green).setOnClickListener(this);
        findViewById(R.id.bt_blue).setOnClickListener(this);
        findViewById(R.id.bt_start).setOnClickListener(this);
        mOutput = (TextView)findViewById(R.id.tv_output);
       if (savedInstanceState == null) {
           FragmentManager m = getSupportFragmentManager();
           CardFragment frag = CardFragment.create("RED", Color.RED);
           m.beginTransaction()
                   .add(R.id.host, frag, "RED_INITIAL")

                   .commit();

           m.beginTransaction()
                   .add(new NoViewFragment(),TAG)
                   .commit();
       }
    }

    private void startExecution(){
        Fragment ft = getSupportFragmentManager().findFragmentByTag(TAG);
        NoViewFragment noview = (NoViewFragment)ft;
        noview.startAsync();
    }

    @Override
    public void onClick(View v) {
        FragmentManager m = getSupportFragmentManager();
        CardFragment cf;
        String tag;
        switch (v.getId()){
            case R.id.bt_blue:
                tag = "BLUE";
                cf = CardFragment.create(tag, Color.BLUE);
                break;
            case R.id.bt_green:
                tag = "GREEN";
                cf = CardFragment.create(tag, Color.GREEN);
                break;
            case R.id.bt_red:
                tag = "RED";
                cf = CardFragment.create(tag, Color.RED);
                break;
            case R.id.bt_start:
                startExecution();
                return;
            default:
                throw new RuntimeException();
        }
        m.beginTransaction()
         .replace(R.id.host,cf,tag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onNewText(String text) {
        mOutput.setText(text);
    }
}
