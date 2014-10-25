package me.aktor.simpleapp.ui.data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.util.Stack;

/**
 * Created by Andrea Tortorella on 10/25/14.
 */
public final class Contract {

    static final String DATABASE = "notes.db";
    static final int VERSION = 2;

    public static final String CONTENT_AUTHORITY = "me.aktor.simpleapp.provider";

    static final String NOTE_TABLE = "notes";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    static interface NoteColumns {
        String TITLE_COL  = "title";
        String DATE_COL = "date";
        String TEXT_COL = "text";
    }


    public final static class Note implements NoteColumns,BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon()
                                .appendPath(NOTE_TABLE)
                                .build();

        public static Uri getNoteUri(long id){
            return  CONTENT_URI.buildUpon()
                               .appendPath(String.valueOf(id))
                               .build();
        }



    }


}
