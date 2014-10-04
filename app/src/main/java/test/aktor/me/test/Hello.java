package test.aktor.me.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Hello extends ActionBarActivity implements View.OnClickListener {

    public static final String LOG_TAG ="HELLO";

    public static final String CURRENT_TEXT = "current_text";

    private EditText mInput;
    private TextView mOutput;
    private String mCurrentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        if (savedInstanceState == null) {
            mCurrentText = getString(R.string.hello_world);
            Log.d(LOG_TAG,"no saved state");
        } else {
            mCurrentText = savedInstanceState.getString(CURRENT_TEXT);
            Log.d(LOG_TAG,"we have a state to resume from");
        }

//        Button button = (Button)findViewById(R.id.btn_update);
        View button = findViewById(R.id.btn_update);
        findViewById(R.id.btn_next).setOnClickListener(this);

//        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ILoveYou.start(Hello.this,mCurrentText); //fixme <------
//            }
//        });
        final String x = "ciao";
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateText();
//                //x = "ciao2";
//            }
//        });

        button.setOnClickListener(this);
        mInput = (EditText)findViewById(R.id.in_text);
        mOutput = (TextView)findViewById(R.id.tv_output);
        mOutput.setText(mCurrentText);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_update) {
            updateText();
        } else {
            start(this,mCurrentText);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_TEXT,mCurrentText);
    }

    private void updateText(){
        Editable text = mInput.getText();
        String string = text.toString();
        mCurrentText = string;
        mOutput.setText(mCurrentText);
    }


//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.hello, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public static void start(Activity activity,String msg){
        Intent message = new Intent(activity,ILoveYou.class);
        message.putExtra(ILoveYou.MESSAGE, msg);
        activity.startActivity(message);
    }
}
