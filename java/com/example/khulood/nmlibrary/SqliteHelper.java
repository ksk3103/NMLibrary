package com.example.khulood.nmlibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class SqliteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydb";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "user_reg";
    public static final String ISSUE_HISTORY = "issue_history";

    public static final String KEY_ID = BaseColumns._ID;
    public static final String KEY_EMAIL = "email";
    public static final String KEY_SID = "sid";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PASSHINT = "passhint";

    public static final String BOOK_ID = "id";
    public static final String KEY_BOOK = "book";
    public static final String AUTHOR = "author";
    public static final String ISSUE_DATE = "issue_date";
    public static final String RETURN_DATE = "return_date";
    public static final String RETURNED_AT = "returned_at";
    public static final String BOOKMARK = "bookmark";




    public static final String SQL_TABLE_USERS = " CREATE TABLE " +
            TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_EMAIL + " TEXT, "
            + KEY_SID + " INTEGER, "
            + KEY_PASSWORD + " TEXT, "
            + KEY_PASSHINT + " TEXT "
            + " ) ";

    public static final String SQL_ISSUE_HISTORY = " CREATE TABLE " +
            ISSUE_HISTORY
            + " ( "
            + BOOK_ID + " INTEGER PRIMARY KEY, "
            + KEY_SID + " INTEGER, "
            + KEY_BOOK + " TEXT, "
            + AUTHOR + " INTEGER, "
            + ISSUE_DATE + " TEXT, "
            + RETURN_DATE + " TEXT, "
            + RETURNED_AT + " TEXT, "
            + BOOKMARK + " TEXT "
            + " ) ";



    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_ISSUE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL(" DROP TABLE IF EXISTS " + ISSUE_HISTORY);

    }

    public long registerUser(User user) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_SID, user.sid);
        cv.put(KEY_EMAIL, user.email);
        cv.put(KEY_PASSWORD, user.password);
        cv.put(KEY_PASSHINT, user.passhint);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_USERS,null,cv);
    }

    public boolean ifUserRegistered(String sid) {
        boolean rv = false;
        String whereclause = KEY_SID + "=?" ;
        String[] whereargs = new String[]{sid};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr = db.query(TABLE_USERS,null,whereclause,whereargs,null,null,null);
        if (csr.getCount() > 0) rv = true;
        csr.close();
        return rv;
    }

    public boolean isEmailExists(String email) {
        boolean rv = false;
        String whereclause = KEY_EMAIL + "=?" ;
        String[] whereargs = new String[]{email};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr = db.query(TABLE_USERS,null,whereclause,whereargs,null,null,null);
        if (csr.getCount() > 0) rv = true;
        csr.close();
        return rv;
    }

    public boolean ifPasswordincorrect(String sid) {
        boolean rv = false;
        String whereclause = KEY_SID + "=?" ;
        String[] whereargs = new String[]{sid};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr = db.query(TABLE_USERS,null,whereclause,whereargs,null,null,null);
        if (csr.getCount() > 0 && csr.moveToFirst())
        {
            csr.getString(csr.getColumnIndex(KEY_PASSWORD));
            rv = true;
        }
        csr.close();
        return rv;
    }


    public String getEmailFromSID(String sid) {
        String rv = "";
        String whereclause = KEY_SID + "=?";
        String[] whereargs = new String[]{sid};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr = db.query(TABLE_USERS,null,whereclause,whereargs,null,null,null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndex(KEY_EMAIL));
        }
        csr.close();
        return rv;
    }

    public String getPassHint(String email) {
        String rv = "";
        String whereclause = KEY_EMAIL + "=?";
        String[] whereargs = new String[]{email};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr = db.query(TABLE_USERS,null,whereclause,whereargs,null,null,null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndex(KEY_PASSHINT));
        }
        csr.close();
        return rv;
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_EMAIL, KEY_SID, KEY_PASSWORD, KEY_PASSHINT},//Selecting columns want to query
                KEY_SID + "=?",
                new String[]{user.sid},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                cursor.close();
                return user1;
            }
        }
        cursor.close();

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean updatePass(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_PASSWORD, password);
        sqLiteDatabase.update(TABLE_USERS, cv, "email = ?", new String[] {email});
        return true;
    }

    public long bookinsert(String sid, String book, String author, String issuedate, String returndate, String returnedat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_SID, sid);
        cv.put(KEY_BOOK, book);
        cv.put(AUTHOR, author);
        cv.put(ISSUE_DATE, issuedate);
        cv.put(RETURN_DATE, returndate);
        cv.put(RETURNED_AT, returnedat);
        return db.insert(ISSUE_HISTORY, null, cv);
    }

    public String idno() {
        SQLiteDatabase db = this.getReadableDatabase();
        String rv = "";
        Cursor c = db.rawQuery(" SELECT id FROM issue_history ORDER BY id DESC LIMIT 1 ", null);
        if (c.moveToFirst()) {
            rv = c.getString(c.getColumnIndex(BOOK_ID));
        }
        c.close();
        return rv;
    }

    public ArrayList<BookIssued> issuehistory(String sid) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<BookIssued> list = new ArrayList<BookIssued>();
        Cursor cursor = db.rawQuery(" SELECT * FROM issue_history WHERE sid = ? ORDER BY id DESC ", new String[] {sid} );
        if (cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    BookIssued Book = new BookIssued();
                    Book.setNo(cursor.getInt(0));
                    Book.setBookTitle(cursor.getString(2));
                    Book.setAuthor(cursor.getString(3));
                    Book.setIssueDate(cursor.getString(4));
                    Book.setReturnDate(cursor.getString(5));
                    Book.setReturnedAt(cursor.getString(6));
                    list.add(Book);
                }while (cursor.moveToNext());
            }
        }cursor.close();
        return list;
    }

    public long bookmark(String bookmark){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BOOKMARK, bookmark);
        return db.insert(ISSUE_HISTORY, null, cv);
    }

    public String getbookmark(String sid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String rv = "";
        Cursor c = db.rawQuery(" SELECT bookmark FROM issue_history WHERE sid = ? ", new String[]{sid});
        if (c.moveToFirst()) {
            rv = c.getString(c.getColumnIndex(BOOKMARK));
        }
        c.close();
        return rv;
    }

}