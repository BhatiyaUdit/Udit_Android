package loanapp.udit.com.loanapplication.DataSync.SearchPartyService.InputParameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SEARCHPARAM {

    @SerializedName("PRTY_ID")
    @Expose
    private Object pRTYID;
    @SerializedName("KYC")
    @Expose
    private String kYC;
    @SerializedName("PRTY_CTGRY_TYP_ID")
    @Expose
    private Object pRTYCTGRYTYPID;
    @SerializedName("PARTY_FULL_NAME")
    @Expose
    private Object pARTYFULLNAME;
    @SerializedName("PARTY_CONTACT")
    @Expose
    private Object pARTYCONTACT;
    @SerializedName("INDV_BIRTH_DT")
    @Expose
    private Object iNDVBIRTHDT;
    @SerializedName("GEO_REGN_NM")
    @Expose
    private Object gEOREGNNM;
    @SerializedName("CREATED_BY")
    @Expose
    private Object cREATEDBY;

    public Object getPRTYID() {
        return pRTYID;
    }

    public void setPRTYID(Object pRTYID) {
        this.pRTYID = pRTYID;
    }

    public String getKYC() {
        return kYC;
    }

    public void setKYC(String kYC) {
        this.kYC = kYC;
    }

    public Object getPRTYCTGRYTYPID() {
        return pRTYCTGRYTYPID;
    }

    public void setPRTYCTGRYTYPID(Object pRTYCTGRYTYPID) {
        this.pRTYCTGRYTYPID = pRTYCTGRYTYPID;
    }

    public Object getPARTYFULLNAME() {
        return pARTYFULLNAME;
    }

    public void setPARTYFULLNAME(Object pARTYFULLNAME) {
        this.pARTYFULLNAME = pARTYFULLNAME;
    }

    public Object getPARTYCONTACT() {
        return pARTYCONTACT;
    }

    public void setPARTYCONTACT(Object pARTYCONTACT) {
        this.pARTYCONTACT = pARTYCONTACT;
    }

    public Object getINDVBIRTHDT() {
        return iNDVBIRTHDT;
    }

    public void setINDVBIRTHDT(Object iNDVBIRTHDT) {
        this.iNDVBIRTHDT = iNDVBIRTHDT;
    }

    public Object getGEOREGNNM() {
        return gEOREGNNM;
    }

    public void setGEOREGNNM(Object gEOREGNNM) {
        this.gEOREGNNM = gEOREGNNM;
    }

    public Object getCREATEDBY() {
        return cREATEDBY;
    }

    public void setCREATEDBY(Object cREATEDBY) {
        this.cREATEDBY = cREATEDBY;
    }

}