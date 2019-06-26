package com.example.khulood.nmlibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khulood.nmlibrary.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess2 {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess2 instance;

    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param mContext
     */
    private DatabaseAccess2(Context mContext) {
        this.openHelper = new Databaseopenhelper2(mContext);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param mContext the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess2 getInstance(Context mContext) {
        if (instance == null) {
            instance = new DatabaseAccess2(mContext);
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

    public ArrayList<book> details(String query, String whereargs) {
        ArrayList<book> list = new ArrayList<book>();
        String[] where= new String[]{whereargs};
        Cursor cursor = database.rawQuery(query, where);
        if (cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    book Book = new book();
                    Book.setTitle(cursor.getString(1));
                    Book.setAuthor(cursor.getString(6));
                    Book.setImage(cursor.getBlob(2));
                    Book.setPublisher(cursor.getString(3));
                    Book.setPublish_date(cursor.getString(4));
                    Book.setISBN(cursor.getString(5));
                    Book.setDescription(cursor.getString(9));
                    Book.setIssue(cursor.getString(10));
                    list.add(Book);
                }while (cursor.moveToNext());
            }
        }cursor.close();
        return list;
    }




    public boolean issue(String issue, String isbn){
        ArrayList<book>list = new ArrayList<book>();
        ContentValues cv = new ContentValues();
        cv.put("Issue_status", issue);
        database.update("books", cv, "ISBN = ?", new String[] {isbn});
        book Book = new book();
        Book.setIssue(issue);
        list.add(Book);
        return true;
    }

    public List<String> getBookName() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Book_name, Author, Book_cover, Issue_status FROM books", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public String getBookAuthor(String BookName) {
        String data = null;
        Cursor cursor = database.rawQuery("SELECT Author FROM books WHERE Book_name = ?", new String[]{BookName});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data = cursor.getString(0);
        }
        cursor.close();
        return data;
    }

    public String getIssueStatus(String BookName) {
        String data = null;
        Cursor cursor = database.rawQuery("SELECT Issue_status FROM books WHERE Book_name = ?", new String[]{BookName});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data = cursor.getString(0);
        }
        cursor.close();
        return data;
    }

    public byte[] getBookImage(String BookName) {
        byte[] data = null;
        Cursor cursor = database.rawQuery("SELECT * FROM books WHERE Book_name = ?", new String[]{BookName});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data = cursor.getBlob(0);
            break;  // Assumption: name is unique
        }
        cursor.close();
        return data;
    }
}