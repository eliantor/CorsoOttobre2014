package test.aktor.me.test.lists;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import test.aktor.me.test.MyApplication;
import test.aktor.me.test.R;

/**
 * Created by eto on 11/10/14.
 */
public class StudentListActivity extends Activity {

    private static final List<AndroidStudent> STUDENTS =
            Arrays.asList(new AndroidStudent("Daniele","Roma"),
                    new AndroidStudent("Annibale","Cosenza"),
                    new AndroidStudent("Michele","Roma"));


    private AdapterView mList;
    private StudentsAdapter mAdapter;

    private AddStudentController mAddStudentController;

    private List<AndroidStudent> mStudentsList;
    private AddStudentController.OnAddStudentListener addListener =
            new AddStudentController.OnAddStudentListener() {
                @Override
                public void onAddStudent(AndroidStudent student) {
                    mStudentsList.add(student);
                    mAdapter.notifyDataSetChanged();
                }
            };
    private AdapterView.OnItemClickListener itemClicker = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AndroidStudent s = mAdapter.getItem(position);
            Toast.makeText(StudentListActivity.this,s.name,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

       // MyApplication app =(MyApplication) getApplication();

        MyApplication app = MyApplication.get();

        mStudentsList = new ArrayList<AndroidStudent>();

        mAddStudentController = new AddStudentController(
                findViewById(R.id.add_student_main));
        mAddStudentController.setOnAddStudentListener(addListener);

        mList = (AdapterView)findViewById(R.id.lv_students);

        mAdapter = new StudentsAdapter(this,mStudentsList);
        mList.setAdapter(mAdapter);

       mList.setOnItemClickListener(itemClicker);

    }


}
