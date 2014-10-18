package test.aktor.me.test.lists;

import android.view.View;
import android.widget.EditText;

import test.aktor.me.test.R;

/**
 * Created by eto on 11/10/14.
 */
class AddStudentController {

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.in_student_add:
                    callAddStudent();
            }
        }
    };

    private EditText mName;
    private EditText mBorn;
    private OnAddStudentListener mListener;

    AddStudentController(View view){
        mName = (EditText)view.findViewById(R.id.in_student_name);
        mBorn = (EditText)view.findViewById(R.id.in_student_born);
        view.findViewById(R.id.in_student_add).setOnClickListener(listener);
    }

    private void callAddStudent(){
        if (mListener != null) {
            String name = mName.getText().toString();
            String born = mBorn.getText().toString();
            AndroidStudent student = new AndroidStudent(name,born);
            mListener.onAddStudent(student);
        }
    }


    public void setOnAddStudentListener(OnAddStudentListener listener){
        mListener = listener;
    }

    public static interface OnAddStudentListener {
        public void onAddStudent(AndroidStudent student);
    }
}







