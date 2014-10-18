package test.aktor.me.test.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import test.aktor.me.test.R;

/**
* Created by eto on 11/10/14.
*/
final class StudentsAdapter extends BaseAdapter {

    private final List<AndroidStudent> mStudents;
    private final LayoutInflater mInflater;

    StudentsAdapter(Context context,List<AndroidStudent> initialStudents){
        mStudents = initialStudents;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mStudents.size();
    }

    @Override
    public AndroidStudent getItem(int position) {
        return mStudents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    // ViewHolder
    private static class ItemCtrl{
        private TextView name;
        private TextView born;

//        ItemCtrl(View view){
//
//        }
//
//        public void bind(AndroidStudent student){
//            mostra lo studente nella view
//        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup adapterView) {
        AndroidStudent student = getItem(position);
        View view;
        ItemCtrl ctrl;
        if (convertView == null){
            view = mInflater.inflate(R.layout.item_student, adapterView, false);
            ctrl = new ItemCtrl();
            TextView name =(TextView) view.findViewById(R.id.tv_student_name);
            TextView born =(TextView)view.findViewById(R.id.tv_student_born);

            ctrl.name = name;
            ctrl.born = born;
            view.setTag(ctrl);
        } else {
            view = convertView;
            ctrl =(ItemCtrl)view.getTag();
        }

        //ctrl.bind(student);
        ctrl.name.setText(student.name);
        ctrl.born.setText(student.born);

        return view;
    }
}





