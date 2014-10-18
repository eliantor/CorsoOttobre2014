package test.aktor.me.test.lists;

import android.view.View;
import android.widget.TextView;

import test.aktor.me.test.R;

/**
 * Created by eto on 11/10/14.
 */
public class ItemCtrl {
    private final TextView name;
    private final TextView born;

    public ItemCtrl(View view){
        name =(TextView) view.findViewById(R.id.tv_student_name);
        born =(TextView)view.findViewById(R.id.tv_student_born);
    }

    public void bind(AndroidStudent student){
        name.setText(student.name);
        born.setText(student.born);
    }
}
