package loanapp.udit.com.loanapplication.DataSync.SearchPartyService.OutputParameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PARTYSEARCHRESULTITEM {

    @SerializedName("PRTY_ID")
    @Expose
    private String pRTYID;
    @SerializedName("KYC")
    @Expose
    private String kYC;
    @SerializedName("PRTY_CTGRY_TYP_ID")
    @Expose
    private String pRTYCTGRYTYPID;
    @SerializedName("PARTY_FULL_NAME")
    @Expose
    private String pARTYFULLNAME;
    @SerializedName("PARTY_CONTACT")
    @Expose
    private String pARTYCONTACT;
    @SerializedName("INDV_BIRTH_DT")
    @Expose
    private String iNDVBIRTHDT;
    @SerializedName("GEO_REGN_NM")
    @Expose
    private String gEOREGNNM;
    @SerializedName("CREATED_BY")
    @Expose
    private String cREATEDBY;
    @SerializedName("ADDR_LINE_1_TXT")
    @Expose
    private String aDDRLINE1TXT;

    public String getPRTYID() {
        return pRTYID;
    }

    public void setPRTYID(String pRTYID) {
        this.pRTYID = pRTYID;
    }

    public String getKYC() {
        return kYC;
    }

    public void setKYC(String kYC) {
        this.kYC = kYC;
    }

    public String getPRTYCTGRYTYPID() {
        return pRTYCTGRYTYPID;
    }

    public void setPRTYCTGRYTYPID(String pRTYCTGRYTYPID) {
        this.pRTYCTGRYTYPID = pRTYCTGRYTYPID;
    }

    public String getPARTYFULLNAME() {
        return pARTYFULLNAME;
    }

    public void setPARTYFULLNAME(String pARTYFULLNAME) {
        this.pARTYFULLNAME = pARTYFULLNAME;
    }

    public String getPARTYCONTACT() {
        return pARTYCONTACT;
    }

    public void setPARTYCONTACT(String pARTYCONTACT) {
        this.pARTYCONTACT = pARTYCONTACT;
    }

    public String getINDVBIRTHDT() {
        return iNDVBIRTHDT;
    }

    public void setINDVBIRTHDT(String iNDVBIRTHDT) {
        this.iNDVBIRTHDT = iNDVBIRTHDT;
    }

    public String getGEOREGNNM() {
        return gEOREGNNM;
    }

    public void setGEOREGNNM(String gEOREGNNM) {
        this.gEOREGNNM = gEOREGNNM;
    }

    public String getCREATEDBY() {
        return cREATEDBY;
    }

    public void setCREATEDBY(String cREATEDBY) {
        this.cREATEDBY = cREATEDBY;
    }

    public String getADDRLINE1TXT() {
        return aDDRLINE1TXT;
    }

    public void setADDRLINE1TXT(String aDDRLINE1TXT) {
        this.aDDRLINE1TXT = aDDRLINE1TXT;
    }

}