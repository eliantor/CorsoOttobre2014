package test.aktor.me.test.lists;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import test.aktor.me.test.R;

/**
 * Created by eto on 10/18/14.
 */
public class AddStudentFragment extends Fragment {

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
    private ResourceProvider mProvider;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnAddStudentListener) {
            mListener = (OnAddStudentListener) activity;
        } else {
            throw new IllegalStateException(AddStudentFragment.class.getName()+
                                            " cannot be used in this activity "+
                                            " you should implement "+
                                            OnAddStudentListener.class.getName());
        }

        if (activity instanceof ResourceProvider){
            mProvider = (ResourceProvider)activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_add_student,container,false);

        mName = (EditText)v.findViewById(R.id.in_student_name);
        mBorn = (EditText)v.findViewById(R.id.in_student_born);
        v.findViewById(R.id.in_student_add).setOnClickListener(listener);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StudentListActivity.Resource provide = mProvider.provide();
        String text = provide.get();
        mName.setText(text);
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

    public static interface ResourceProvider {
        public StudentListActivity.Resource provide();
    }
}













