package com.example.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEN = "ten";
    private static final String COLUMN_MSSV = "mssv";
    private static final String COLUMN_LOP = "lop";
    private static final String COLUMN_SDT = "sdt";
    private static final String COLUMN_NAM = "nam";
    private static final String COLUMN_CHUYENNGANH = "chuyennganh";
    private static final String COLUMN_PTBT = "ptbt";
    private static final String COLUMN_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TEN + " TEXT, " +
                COLUMN_MSSV + " TEXT, " +
                COLUMN_LOP + " TEXT, " +
                COLUMN_SDT + " TEXT, " +
                COLUMN_NAM + " INTEGER, " +
                COLUMN_CHUYENNGANH + " TEXT, " +
                COLUMN_PTBT + " TEXT, " +
                COLUMN_IMAGE + " BLOB)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertStudent(String ten, String mssv, String lop, String sdt, int nam, String chuyennganh, String ptbt, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEN, ten);
        values.put(COLUMN_MSSV, mssv);
        values.put(COLUMN_LOP, lop);
        values.put(COLUMN_SDT, sdt);
        values.put(COLUMN_NAM, nam);
        values.put(COLUMN_CHUYENNGANH, chuyennganh);
        values.put(COLUMN_PTBT, ptbt);
        values.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}