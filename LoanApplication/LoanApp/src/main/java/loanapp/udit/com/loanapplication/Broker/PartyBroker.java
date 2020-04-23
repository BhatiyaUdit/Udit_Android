package loanapp.udit.com.loanapplication.Broker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import loanapp.udit.com.loanapplication.CommonApplicationUtil;
import loanapp.udit.com.loanapplication.DBContract.PartyContract;
import loanapp.udit.com.loanapplication.DBHelper.LoanAppDBHelper;
import loanapp.udit.com.loanapplication.ViewLayerBeans.SearchPartyBean;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;
import static loanapp.udit.com.loanapplication.DBContract.PartyContract.PartySearchBaseClass.DELETE_PARTY_SEARCH_ENTRIES;

// This class will interact with the database
public class PartyBroker {
    LoanAppDBHelper loanAppDBHelper;
    SQLiteDatabase database;
    ContentValues cv;

    public PartyBroker() {
        loanAppDBHelper =new LoanAppDBHelper(CommonApplicationUtil.applicationContext);
    }

    public ArrayList<SearchPartyBean> getAllParties() {
        database = loanAppDBHelper.getReadableDatabase();
        ArrayList<SearchPartyBean> searchPartyList  = new ArrayList<>();
        String partyListQuery = "SELECT * FROM " + PartyContract.PartySearchBaseClass.TABLE_PARTY_SEARCH;
        Cursor searchPartyCursor = database.rawQuery(partyListQuery, null);
        try {
            int rows = searchPartyCursor.getCount();
            SearchPartyBean partyBean;
            searchPartyCursor.moveToFirst();
            for (int i = 0; i < rows; i++) {
                partyBean = new SearchPartyBean();
                partyBean.setPrtyId(searchPartyCursor.getLong(searchPartyCursor.getColumnIndex(PartyContract.PartySearchBaseClass.COLUMN_PRTY_ID)));
                partyBean.setPartyFullName(searchPartyCursor.getString(searchPartyCursor.getColumnIndex(PartyContract.PartySearchBaseClass.COLUMN_PARTY_FULL_NAME)));
                partyBean.setKyc(searchPartyCursor.getString(searchPartyCursor.getColumnIndex(PartyContract.PartySearchBaseClass.COLUMN_KYC)));
                partyBean.setDateOfBirth(searchPartyCursor.getString(searchPartyCursor.getColumnIndex(PartyContract.PartySearchBaseClass.COLUMN_INDV_BIRTH_DT)));
                partyBean.setPartyContact(searchPartyCursor.getString(searchPartyCursor.getColumnIndex(PartyContract.PartySearchBaseClass.COLUMN_PARTY_CONTACT)));
                searchPartyList.add(partyBean);
                searchPartyCursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            searchPartyCursor.close();
            database.close();
        }
        return searchPartyList;
    }

    public void insertPartyList(ArrayList<SearchPartyBean> searchPartyList) {
        database= loanAppDBHelper.getWritableDatabase();
        database.execSQL(DELETE_PARTY_SEARCH_ENTRIES);
        for (int i=0 ;i< searchPartyList.size();i++){
            cv = new ContentValues();
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_PARTY_FULL_NAME, searchPartyList.get(i).getPartyFullName());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_PRTY_ID, searchPartyList.get(i).getPrtyId());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_KYC, searchPartyList.get(i).getKyc());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_INDV_BIRTH_DT, searchPartyList.get(i).getDateOfBirth());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_PARTY_CONTACT, searchPartyList.get(i).getPartyContact());
            long id= database.insertWithOnConflict(PartyContract.PartySearchBaseClass.TABLE_PARTY_SEARCH,null,cv, CONFLICT_REPLACE);

        }
        database.close();
    }
}
