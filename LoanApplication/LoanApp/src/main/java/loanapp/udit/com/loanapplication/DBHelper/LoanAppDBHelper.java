package loanapp.udit.com.loanapplication.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import loanapp.udit.com.loanapplication.DBContract.PartyContract;

public class LoanAppDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="LOAN_APP_DATABASE.db";
    public static final int DATABASE_VERSION =1;


    public LoanAppDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PartyContract.PartySearchBaseClass.CREATE_PARTY_SEARCH_TABLE_V1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
