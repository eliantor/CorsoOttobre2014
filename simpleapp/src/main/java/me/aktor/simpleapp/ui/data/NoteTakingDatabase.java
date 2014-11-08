package me.aktor.simpleapp.ui.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static me.aktor.simpleapp.ui.data.Contract.*;
/**
 * Created by Andrea Tortorella on 10/25/14.
 */
class NoteTakingDatabase extends SQLiteOpenHelper {
// see http://www.sqlite.org/docs.html
    public NoteTakingDatabase(Context context) {
        super(context, Contract.DATABASE,
              null, Contract.VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
// set pragmas on the database
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(DROP_NOTE_TABLE);
        }
        onCreate(db);
    }

    private static final String CREATE_NOTE_TABLE =
            "CREATE TABLE "+NOTE_TABLE + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NoteColumns.TITLE_COL + " TEXT NOT NULL,"
                + NoteColumns.DATE_COL + " TEXT,"
                + NoteColumns.TEXT_COL+ " TEXT NOT NULL DEFAULT '',"
                + SyncColumns.SYNC_STATUS+ " INTEGER NOT NULL DEFAULT "
                    +SyncStatuses.NOT_SYNCED
                + ")";

    private static final String DROP_NOTE_TABLE =
            "DROP TABLE IF EXISTS "+NOTE_TABLE;


}
