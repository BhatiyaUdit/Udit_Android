package com.example.ub61555.myapplication.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.ub61555.myapplication.Data.PartyContract.PartyBaseClass.ALTER_TABLE_TO_ADD_DOB;
import static com.example.ub61555.myapplication.Data.PartyContract.PartyBaseClass.CREATE_TABLE_STATEMENT;
import static com.example.ub61555.myapplication.Data.PartyContract.PartyBaseClass.SQL_DELETE_ENTRIES;

public class PartyDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="CUSTOMER.db";
    public static final int DATABASE_VERSION = 3;
    public PartyDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ALTER_TABLE_TO_ADD_DOB);
    }
}
