package me.aktor.simpleapp.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import me.aktor.simpleapp.R;
import me.aktor.simpleapp.ui.data.Contract;

/**
 * Created by Andrea Tortorella on 10/25/14.
 */
public class FragmentList extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView mListView;
    private NotesAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        mListView = (ListView)view.findViewById(R.id.list);
        mAdapter = new NotesAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LoaderManager loaders = getLoaderManager();
        loaders.initLoader(1,null,this);
    }

    private static final String[] PROJECTION ={
            Contract.Note._ID,
            Contract.Note.TITLE_COL,
            Contract.Note.DATE_COL
    };

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader loader = new CursorLoader(getActivity(),
                Contract.Note.CONTENT_URI,PROJECTION,null,null,null);
        loader.setUpdateThrottle(3000);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> objectLoader) {
        mAdapter.swapCursor(null);
    }

    private static class ViewHolder {
        private final TextView title;
        private final TextView date;

        private ViewHolder(TextView title, TextView date) {
            this.title = title;
            this.date = date;
        }
    }


    private class NotesAdapter  extends CursorAdapter{
        private final LayoutInflater mInflater;

        public NotesAdapter(Context context) {
            super(context, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            mInflater = LayoutInflater.from(context);
        }
/*  what it does internally
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Object item = getItem(position);
            Cursor c = (Cursor)item;
            c.moveToPosition(position);
            if (convertView == null){
                convertView = newView(mContext,c,parent);
            }
            bindView(convertView,mContext,c);
            return convertView;
            //return super.getView(position, convertView, parent);
        }
*/
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View view  = mInflater.inflate(R.layout.list_item,viewGroup,false);
            TextView t =(TextView)view.findViewById(R.id.tv_item_title);
            TextView d = (TextView)view.findViewById(R.id.tv_date);
            ViewHolder h = new ViewHolder(t,d);
            view.setTag(h);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder h = (ViewHolder)view.getTag();
            int titleIndex = cursor.getColumnIndexOrThrow(Contract.Note.TITLE_COL);
            int dateIndex = cursor.getColumnIndexOrThrow(Contract.Note.DATE_COL);
            String title = cursor.getString(titleIndex);
            String date = cursor.getString(dateIndex);
            h.title.setText(title);
            h.date.setText(date);
        }
    }
}
