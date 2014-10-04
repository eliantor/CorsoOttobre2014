package test.aktor.me.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by eto on 04/10/14.
 */
public class ILoveYou extends ActionBarActivity {
    private static final int CODE = 1;
    public static final String MESSAGE = "message_extra";

    private TextView mText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iloveyou_activity);
        TextView view  = (TextView)findViewById(R.id.tv_result);
        mText = view;

        Intent intent = getIntent(); // ^^^
        final String msg = intent.getStringExtra(MESSAGE);

        view.setText(msg);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LetMeJump.start(ILoveYou.this,CODE,msg);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE) {
            if (resultCode == RESULT_OK){
                String messageUppercase = data.getStringExtra(LetMeJump.RESPONSE);
                mText.setText(messageUppercase);
            } else if (resultCode == RESULT_CANCELED){
                Log.wtf("WTF","CANCELLED");
                Toast.makeText(this,"Canceled",Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}








