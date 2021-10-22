package com.example.gymapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Data.db";
    public static final int DATABASE_VERSION = 10;
    public static final String CLASS_TABLE = "CLASS_TABLE";
    public static final String COL1 = "Class_Type";
    public static final String COL2 = "Class_Description";


    public DBHelper(Context context) {

        super(context, "Data.db", null, DATABASE_VERSION);
        SQLiteDatabase fitnessdb = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase gymDB) {
        gymDB.execSQL("create Table instructor(username Text primary key, password TEXT)");
        gymDB.execSQL("create Table member(username Text primary key, password TEXT)");
        gymDB.execSQL("create Table admin(username Text primary key, password TEXT)");
        String adminAccount = "INSERT INTO admin(username, password) VALUES ('admin', 'admin123'); ";

        String createTable = "CREATE TABLE" + CLASS_TABLE + "( " + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, )";
        gymDB.execSQL("create Table adminCLass(className Text primary key, classDescription TEXT)");
        gymDB.execSQL("create Table instructorCLass(instructor Text primary key, className TEXT, capacity INT, dateNtime LONG)");
        gymDB.execSQL("create Table classList(className Text primary key, className TEXT)");

        gymDB.execSQL(adminAccount);


    }

    @Override
    public void onUpgrade(SQLiteDatabase gymDB, int i, int i1) {
        gymDB.execSQL("drop Table if exists instructor");
        gymDB.execSQL("drop Table if exists member");
        gymDB.execSQL("drop Table if exists admin");
        onCreate(gymDB);


    }

    public boolean insertData(String username, String password, String table) {
        SQLiteDatabase gymDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = gymDB.insert(table, null, contentValues);
        if (result == -1)
            return false;
        return true;

    }


    public Boolean checkUsername(String username, String table) {
        SQLiteDatabase gymDB = this.getWritableDatabase();
        Cursor cursor = null;
        if (table.equals("instructor"))
            cursor = gymDB.rawQuery("Select * from instructor where username = ?", new String[]{username});
        else if (table.equals("member"))
            cursor = gymDB.rawQuery("Select * from member where username = ?", new String[]{username});
        else if (table.equals("admin"))
            cursor = gymDB.rawQuery("Select * from admin where username = ?", new String[]{username});

        if (cursor.getCount() > 0)
            return true;
        return false;
    }

    public Boolean checkUsernamePassword(String username, String password, String table) {
        SQLiteDatabase gymDB = this.getWritableDatabase();
        Cursor cursor = null;
        if (table.equals("instructor"))
            cursor = gymDB.rawQuery("Select * from instructor where username = ? and password = ?", new String[]{username, password});
        else if (table.equals("member"))
            cursor = gymDB.rawQuery("Select * from member where username = ? and password = ?", new String[]{username, password});
        else if (table.equals("admin"))
            cursor = gymDB.rawQuery("Select * from admin where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        return false;
    }

    public boolean addClass(String classType, String classDescription) {
        SQLiteDatabase gymDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, classType);
        contentValues.put(COL2, classDescription);

        long insert = gymDB.insert(CLASS_TABLE, null, contentValues);
        if (insert == -1)
            return false;
        return true;
    }

    public Cursor getViewClasses() {
        SQLiteDatabase gymDB = this.getWritableDatabase();
        Cursor cursor = gymDB.rawQuery("Select * from " + CLASS_TABLE, null);
        return cursor;
    }

    public boolean updateData (String classType, String classDescription) {

        SQLiteDatabase gymDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, classType);
        contentValues.put(COL2, classDescription);
        gymDB.update(CLASS_TABLE, contentValues, "Class Type = ? ", new String[] {classType});

        return true;
    }

    public Integer deleteClass(String classType) {
        SQLiteDatabase gymDB = this.getWritableDatabase();
        return gymDB.delete(CLASS_TABLE, "classType = ?", new String[] {classType});
    }

    public boolean deleteUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(CLASS_TABLE, "classType + = ?" , new String[]{user}) > 0;
    }
}
