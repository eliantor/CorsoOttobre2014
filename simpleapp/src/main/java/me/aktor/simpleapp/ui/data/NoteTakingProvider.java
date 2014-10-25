package me.aktor.simpleapp.ui.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Andrea Tortorella on 10/25/14.
 */
public class NoteTakingProvider extends ContentProvider {
    private NoteTakingDatabase mDb;

    private static final UriMatcher MATCHER = buildMatcher();

    private static final int ALL_NOTES = 1;
    private static final int ONE_NOTES = 2;


    private static UriMatcher buildMatcher(){
        UriMatcher m = new UriMatcher(UriMatcher.NO_MATCH);
        m.addURI(Contract.CONTENT_AUTHORITY,Contract.NOTE_TABLE,ALL_NOTES);
        m.addURI(Contract.CONTENT_AUTHORITY,Contract.NOTE_TABLE+"/#",ONE_NOTES);
        return m;
    }

    @Override
    public boolean onCreate() {
        mDb = new NoteTakingDatabase(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String where,
                        String[] whereParams,
                        String sortOrder) {
        final int op = MATCHER.match(uri);
        SQLiteDatabase db = mDb.getReadableDatabase();
        String table;
        String finalWhere;
        switch (op){
            case ALL_NOTES:
                table = Contract.NOTE_TABLE;
                finalWhere = where;
                break;
            case ONE_NOTES:
                table = Contract.NOTE_TABLE;
                long id = ContentUris.parseId(uri);
                if (TextUtils.isEmpty(where)){
                    finalWhere = "_id = "+id;
                } else {
                    finalWhere = where+" AND (_id = "+id+")";
                }
                break;
            default:
                throw new UnsupportedOperationException("cannot do this");
        }
        Cursor c = db.query(table, projection, finalWhere, whereParams, null, null, sortOrder);

        if (c == null) {
            return null;
        }
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int op = MATCHER.match(uri);
        switch (op) {
            case ALL_NOTES:
                Log.d("PROVIDER","Inserting in: "+uri);
                long id =insertNote(values);
                if (id == -1) {
                    return null;
                }
                Uri newNoteUri =
                        ContentUris.withAppendedId(uri, id);
                Log.d("PROVIDER","Inserted: "+newNoteUri);
                getContext().getContentResolver().notifyChange(uri,null);
                return newNoteUri;
            default:
                throw new UnsupportedOperationException("You cannot do this");
        }
    }

    private long insertNote(ContentValues values){
        SQLiteDatabase database = mDb.getWritableDatabase();
        long rowId = database.insert(Contract.NOTE_TABLE, null, values);
        return rowId;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Cannot delete");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Cannot delete");
    }


}
