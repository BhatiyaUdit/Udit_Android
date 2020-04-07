package com.example.ub61555.myapplication.Broker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ub61555.myapplication.Bean.PartyBean;
import com.example.ub61555.myapplication.CommonApplication;
import com.example.ub61555.myapplication.Data.PartyContract;
import com.example.ub61555.myapplication.Data.PartyDBHelper;
import com.example.ub61555.myapplication.Model.PartyModel;

import java.util.ArrayList;
import java.util.List;

public class PartyBroker {
    PartyDBHelper dbHelper;
    SQLiteDatabase db;
    ContentValues cv;

    public PartyBroker(){
        dbHelper = new PartyDBHelper(CommonApplication.applicationContext);
    }

    public void insertParty(PartyModel partyModel){
        db= dbHelper.getWritableDatabase();
        cv = new ContentValues();
        cv.put(PartyContract.PartyBaseClass.CLOUMN_NAME_P_NAME, partyModel.getPartyName());
        cv.put(PartyContract.PartyBaseClass.COLUMN_NAME_DOB, partyModel.getPartyDOB());
        long id= db.insert(PartyContract.PartyBaseClass.TABLE_NAME,null,cv);
        db.close();

    }

    public List<PartyBean> getAllPartiesFromDB() {
        db = dbHelper.getReadableDatabase();
        List<PartyBean> partiesFromDB= new ArrayList<PartyBean>();
        Cursor c = db.rawQuery("SELECT * FROM " + PartyContract.PartyBaseClass.TABLE_NAME, null);
        try {
            int rows = c.getCount();
            PartyBean partyBean;
            c.moveToFirst();
            for (int i = 0; i < rows; i++) {
                partyBean = new PartyBean();
                partyBean.setPartyId(c.getInt(c.getColumnIndex(PartyContract.PartyBaseClass.CLOUMN_NAME_ID)));
                partyBean.setPartyName(c.getString(c.getColumnIndex(PartyContract.PartyBaseClass.CLOUMN_NAME_P_NAME)));
                partiesFromDB.add(partyBean);
                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.close();
            db.close();
        }
        return partiesFromDB;
    }

    public PartyBean getPartyById(Integer partyId) {
        db = dbHelper.getReadableDatabase();
        PartyBean partyBean = new PartyBean();
        String whereClause = PartyContract.PartyBaseClass.CLOUMN_NAME_ID +"= ?";
        String[] whereArgs = new String[] {
               partyId.toString()
        };
        Cursor c= db.query(PartyContract.PartyBaseClass.TABLE_NAME, null,whereClause ,whereArgs, null,null,null );
        try{
            c.moveToFirst();
            partyBean.setPartyId(c.getInt(c.getColumnIndex(PartyContract.PartyBaseClass.CLOUMN_NAME_ID)));
            partyBean.setPartyName(c.getString(c.getColumnIndex(PartyContract.PartyBaseClass.CLOUMN_NAME_P_NAME)));
            partyBean.setPartyDOB(c.getString(c.getColumnIndex(PartyContract.PartyBaseClass.COLUMN_NAME_DOB)));
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            c.close();
            db.close();
        }
        return partyBean;
    }

    public void updateParty(PartyModel partyModel) {
        db= dbHelper.getWritableDatabase();
        cv = new ContentValues();
        cv.put(PartyContract.PartyBaseClass.CLOUMN_NAME_P_NAME, partyModel.getPartyName());
        cv.put(PartyContract.PartyBaseClass.COLUMN_NAME_DOB, partyModel.getPartyDOB());
        String whereClause = PartyContract.PartyBaseClass.CLOUMN_NAME_ID +"= ?";
        String[] whereArgs = new String[] {
                partyModel.getPartyId().toString()
        };
        long id= db.update(PartyContract.PartyBaseClass.TABLE_NAME, cv,whereClause, whereArgs );
        db.close();
    }

    public List<PartyBean> searchParty(String searchText) {
        db = dbHelper.getReadableDatabase();
        List<PartyBean> partiesFromDB= new ArrayList<PartyBean>();
        Cursor c = db.rawQuery("SELECT * FROM " + PartyContract.PartyBaseClass.TABLE_NAME + " WHERE "+ PartyContract.PartyBaseClass.CLOUMN_NAME_P_NAME+" LIKE '%"+searchText + "%'", null
        );
        try {
            int rows = c.getCount();
            PartyBean partyBean;
            c.moveToFirst();
            for (int i = 0; i < rows; i++) {
                partyBean = new PartyBean();
                partyBean.setPartyId(c.getInt(c.getColumnIndex(PartyContract.PartyBaseClass.CLOUMN_NAME_ID)));
                partyBean.setPartyName(c.getString(c.getColumnIndex(PartyContract.PartyBaseClass.CLOUMN_NAME_P_NAME)));
                partiesFromDB.add(partyBean);
                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.close();
            db.close();
        }
        return partiesFromDB;
    }

    public void deleteParty(Integer partyId) {
        db= dbHelper.getWritableDatabase();
        cv = new ContentValues();
        String whereClause = PartyContract.PartyBaseClass.CLOUMN_NAME_ID +"= ?";
        String[] whereArgs = new String[] {
                partyId.toString()
        };
        long id= db.delete(PartyContract.PartyBaseClass.TABLE_NAME,whereClause, whereArgs );
        db.close();
    }
}
