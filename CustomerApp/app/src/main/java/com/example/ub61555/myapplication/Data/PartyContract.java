package com.example.ub61555.myapplication.Data;

import android.provider.BaseColumns;

public final class PartyContract {

    public static class PartyBaseClass implements BaseColumns{
        public static final String TABLE_NAME = "PARTY_DETAILS";
        public static final String CLOUMN_NAME_ID = "PARTY_ID";
        public static final String CLOUMN_NAME_P_NAME = "PARTY_NAME";
        public static final String COLUMN_NAME_DOB ="DATE_OF_BIRTH";

        public static final String CREATE_TABLE_STATEMENT = " CREATE TABLE " + TABLE_NAME + " ( "+
                CLOUMN_NAME_ID + " INTEGER PRIMARY KEY ," +
                CLOUMN_NAME_P_NAME + "  TEXT )" ;

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static final String ALTER_TABLE_TO_ADD_DOB = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " +COLUMN_NAME_DOB + " DATE ";
    }
}

