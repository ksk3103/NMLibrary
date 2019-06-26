package com.example.khulood.nmlibrary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public boolean ifUserExists(String sid) {
        boolean rv = false;
        String whereclause = " KEY_SID = ? ";
        String[] whereargs = new String[]{sid};
        Cursor csr = database.query(" user_info ",null,whereclause,whereargs,null,null,null);
        if (csr.getCount() > 0) rv = true;
        csr.close();
        return rv;
    }

    public String getUserNameFromSID(String sid) {
        String rv = "";
        String whereclause = " KEY_SID = ? ";
        String[] whereargs = new String[]{sid};
        Cursor csr = database.query(" user_info ",null,whereclause,whereargs,null,null,null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndex("KEY_NAME"));
        }
        csr.close();
        return rv;
    }

    public String getUserCourseFromSID(String sid) {
        String rv = "";
        String whereclause = " KEY_SID = ? ";
        String[] whereargs = new String[]{sid};
        Cursor csr = database.query(" user_info ", null, whereclause, whereargs, null, null, null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndex("KEY_COURSE"));
        }
        csr.close();
        return rv;
    }


    public byte[] getImage(String sid) {
        byte[] data = null;
        Cursor cursor = database.rawQuery("SELECT KEY_IMAGE FROM USER_INFO WHERE KEY_SID = ?", new String[]{sid});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data = cursor.getBlob(0);
            break;  // Assumption: name is unique
        }
        cursor.close();
        return data;
    }
}