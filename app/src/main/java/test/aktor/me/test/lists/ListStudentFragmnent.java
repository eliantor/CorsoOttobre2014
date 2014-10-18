package test.aktor.me.test.lists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.aktor.me.test.R;

/**
 * Created by eto on 10/18/14.
 */
public class ListStudentFragmnent extends Fragment {

    private AdapterView mList;
    private StudentsAdapter mAdapter;
    private List<AndroidStudent> mStudentsList;

    private AdapterView.OnItemClickListener itemClicker = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AndroidStudent s = mAdapter.getItem(position);
            Toast.makeText(getActivity(), s.name, Toast.LENGTH_SHORT).show();
        }


    };


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_list_students,container,false);

        mStudentsList = new ArrayList<AndroidStudent>();

        mList = (AdapterView)v.findViewById(R.id.lv_students);

        mAdapter = new StudentsAdapter(getActivity(),mStudentsList);
        mList.setAdapter(mAdapter);

        mList.setOnItemClickListener(itemClicker);

        return v;
    }

    public void addStudent(AndroidStudent student){
        mStudentsList.add(student);
        mAdapter.notifyDataSetChanged();
    }
}
