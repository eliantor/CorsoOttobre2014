package me.aktor.simpleapp.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Date;

import me.aktor.simpleapp.R;
import me.aktor.simpleapp.ui.data.Contract;

/**
 * Created by Andrea Tortorella on 10/25/14.
 */
public class FragmentAdd extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add,container,false);
        view.findViewById(R.id.add_new_item).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        insertNote();
    }

    private void insertNote(){
        ContentResolver cr = getActivity().getContentResolver();
        ContentValues val = new ContentValues();
        val.put(Contract.Note.TITLE_COL,"Title");
        val.put(Contract.Note.TEXT_COL,"Amazing note");
        val.put(Contract.Note.DATE_COL,new Date().toString());

        Uri insert = cr.insert(Contract.Note.CONTENT_URI, val);

    }
}
