package loanapp.udit.com.loanapplication.DBContract;

import android.provider.BaseColumns;

public final class PartyContract{

    public static final String TEXT_DATA_TYPE = "TEXT";
    public static final String INTEGER_DATA_TYPE = "INTEGER";
    public static final String DATE_DATA_TYPE = "DATE";

    public static class PartySearchBaseClass implements BaseColumns{

        public static final String TABLE_PARTY_SEARCH= "PARTY_SEARCH_DATA";
        public static final String COLUMN_PRTY_ID= "PRTY_ID";
        public static final String COLUMN_KYC= "KYC";
        public static final String COLUMN_PRTY_CTGRY_TYP_ID= "PRTY_CTGRY_TYP_ID";
        public static final String COLUMN_PARTY_FULL_NAME= "PARTY_FULL_NAME";
        public static final String COLUMN_PARTY_CONTACT= "PARTY_CONTACT";
        public static final String COLUMN_INDV_BIRTH_DT= "INDV_BIRTH_DT";
        public static final String COLUMN_GEO_REGN_NM= "GEO_REGN_NM";
        public static final String COLUMN_CREATED_BY= "CREATED_BY";
        public static final String COLUMN_ADDR_LINE_1_TXT= "ADDR_LINE_1_TXT";

        public static final String CREATE_PARTY_SEARCH_TABLE_V1 = " CREATE TABLE " + TABLE_PARTY_SEARCH + " ( " +
                COLUMN_PRTY_ID              +" " + INTEGER_DATA_TYPE    + " PRIMARY KEY" +  " , "+
                COLUMN_KYC                  +" " + TEXT_DATA_TYPE       +                   " , "+
                COLUMN_PRTY_CTGRY_TYP_ID    +" " + INTEGER_DATA_TYPE    +                   " , "+
                COLUMN_PARTY_FULL_NAME      +" " + TEXT_DATA_TYPE       +                   " , "+
                COLUMN_PARTY_CONTACT        +" " + TEXT_DATA_TYPE       +                   " , "+
                COLUMN_INDV_BIRTH_DT        +" " + DATE_DATA_TYPE       +                   " , "+
                COLUMN_GEO_REGN_NM          +" " + TEXT_DATA_TYPE       +                   " , "+
                COLUMN_CREATED_BY           +" " + TEXT_DATA_TYPE       +                   " , "+
                COLUMN_ADDR_LINE_1_TXT      +" " + TEXT_DATA_TYPE       +
                " )";

        public static final String DELETE_PARTY_SEARCH_ENTRIES =
                "DELETE FROM  " + TABLE_PARTY_SEARCH;
    }
}
