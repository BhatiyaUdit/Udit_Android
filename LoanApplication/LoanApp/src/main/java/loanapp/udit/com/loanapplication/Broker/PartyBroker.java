package loanapp.udit.com.loanapplication.Broker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import loanapp.udit.com.loanapplication.CommonApplicationUtil;
import loanapp.udit.com.loanapplication.DBContract.PartyContract;
import loanapp.udit.com.loanapplication.DBHelper.LoanAppDBHelper;
import loanapp.udit.com.loanapplication.Model.PartyModel;
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

    public void insertPartyList(ArrayList<PartyModel> searchPartyList) {
        database= loanAppDBHelper.getWritableDatabase();
        database.execSQL(DELETE_PARTY_SEARCH_ENTRIES);
        for (int i=0 ;i< searchPartyList.size();i++){
            cv = new ContentValues();
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_PRTY_ID, searchPartyList.get(i).getPrtyId());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_KYC, searchPartyList.get(i).getKyc());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_PRTY_CTGRY_TYP_ID,searchPartyList.get(i).getPrtyCtgryTypId());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_PARTY_FULL_NAME, searchPartyList.get(i).getPartyFullName());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_PARTY_CONTACT, searchPartyList.get(i).getPartyContact());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_INDV_BIRTH_DT, searchPartyList.get(i).getDateOfBirth());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_GEO_REGN_NM, searchPartyList.get(i).getGeoRegion());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_CREATED_BY,searchPartyList.get(i).getCreatedBy());
            cv.put(PartyContract.PartySearchBaseClass.COLUMN_ADDR_LINE_1_TXT,searchPartyList.get(i).getAddrLineTxt());
            long id= database.insertWithOnConflict(PartyContract.PartySearchBaseClass.TABLE_PARTY_SEARCH,null,cv, CONFLICT_REPLACE);
            if(id== -1){
                CommonApplicationUtil.ErrorMessage="Error while insertion";
            }
        }
        database.close();
    }

    public ArrayList<SearchPartyBean> getAllParties(String searchText, int sortBy) {
        database = loanAppDBHelper.getReadableDatabase();
        ArrayList<SearchPartyBean> searchPartyList  = new ArrayList<>();
        String partyListQuery = "SELECT * FROM " + PartyContract.PartySearchBaseClass.TABLE_PARTY_SEARCH +
                                " WHERE "
                                + PartyContract.PartySearchBaseClass.COLUMN_PARTY_FULL_NAME+" LIKE '%"+searchText + "%'"
                                +" OR "+ PartyContract.PartySearchBaseClass.COLUMN_KYC+" LIKE '%"+searchText + "%'"
                                +" " + getOrderByClause(sortBy);

        Cursor searchPartyCursor = database.rawQuery(partyListQuery, null);
        Log.i("QUERY WHILE GETTING DATA", partyListQuery);
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

    public ArrayList<SearchPartyBean> getAllParties(int sortBy) {
        database = loanAppDBHelper.getReadableDatabase();
        ArrayList<SearchPartyBean> searchPartyList  = new ArrayList<>();
        String partyListQuery = "SELECT * FROM " + PartyContract.PartySearchBaseClass.TABLE_PARTY_SEARCH +getOrderByClause(sortBy);

        Cursor searchPartyCursor = database.rawQuery(partyListQuery, null);
        Log.i("QUERY WHILE GETTING DATA", partyListQuery);
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

    public String getOrderByClause(int sortBy){
        String orderByClause ="";
        if(sortBy==0){
            orderByClause =  " ORDER BY  " + PartyContract.PartySearchBaseClass.COLUMN_PARTY_FULL_NAME;
        }else if(sortBy==1){
            orderByClause =  " ORDER BY  " + PartyContract.PartySearchBaseClass.COLUMN_PARTY_FULL_NAME + " DESC";
        }else if(sortBy==2){
            orderByClause =  " ORDER BY  " + PartyContract.PartySearchBaseClass.COLUMN_PRTY_ID + " DESC";
        }else if(sortBy==3){
            orderByClause =  " ORDER BY  " + PartyContract.PartySearchBaseClass.COLUMN_PRTY_ID ;
        }
        return  orderByClause;
    }

}
