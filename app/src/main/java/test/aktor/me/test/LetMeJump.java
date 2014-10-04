package test.aktor.me.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class LetMeJump extends ActionBarActivity implements View.OnClickListener {

    private static final String MESSAGE = "MESSAGE";

    public static final String RESPONSE = "RESPONSE";

    public static void start(Activity activity,int code,String message){
        Intent i = new Intent(activity,LetMeJump.class);
        i.putExtra(MESSAGE,message);
        activity.startActivityForResult(i,code);
    }

    private String mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_le_me_jump);

        Intent intent = getIntent();
        String extra = intent.getStringExtra(MESSAGE);
        mMessage = extra;
        mMessage = mMessage != null?mMessage.toUpperCase():mMessage;

        TextView preview = (TextView)findViewById(R.id.tv_elaborate);
        preview.setText(mMessage);

        findViewById(R.id.btn_ok).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent response = new Intent();
        response.putExtra(RESPONSE,mMessage);
        setResult(RESULT_OK, response);
        finish();
    }
}








