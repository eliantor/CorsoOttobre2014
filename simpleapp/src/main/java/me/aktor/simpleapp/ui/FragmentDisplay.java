package me.aktor.simpleapp.ui;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;

import me.aktor.simpleapp.ui.data.Contract;

/**
 * Created by Andrea Tortorella on 11/8/14.
 */
public class FragmentDisplay extends Fragment {

    private void doSomething(){
        try{
            queryItem(90);
        } catch (Exception e){

        }
    }

    private void queryItem(long id){
        Uri noteUri = Contract.Note.getNoteUri(id);
        Cursor query = getActivity().getContentResolver().query(noteUri, null, null, null, null);
        try {
            int titleIdx = query.getColumnIndex(Contract.Note.TITLE_COL);
            while (query.moveToNext()) {

            }

            if (query.moveToFirst()) {
                String title = query.getString(titleIdx);
            }
        }
//        catch (Exception e){
//            ///
//        }
        finally {
            if(query!=null && !query.isClosed())query.close();
        }
    }
}
