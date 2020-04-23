package loanapp.udit.com.loanapplication.DataSync.SearchPartyService.OutputParameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutputParameters {

    @SerializedName("@xmlns")
    @Expose
    private String xmlns;
    @SerializedName("@xmlns:xsi")
    @Expose
    private String xmlnsXsi;
    @SerializedName("PARTY_SEARCH_RESULT")
    @Expose
    private PARTYSEARCHRESULT pARTYSEARCHRESULT;
    @SerializedName("P_ERROR_CD")
    @Expose
    private String pERRORCD;
    @SerializedName("P_ERROR_MSG")
    @Expose
    private String pERRORMSG;

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getXmlnsXsi() {
        return xmlnsXsi;
    }

    public void setXmlnsXsi(String xmlnsXsi) {
        this.xmlnsXsi = xmlnsXsi;
    }

    public PARTYSEARCHRESULT getPARTYSEARCHRESULT() {
        return pARTYSEARCHRESULT;
    }

    public void setPARTYSEARCHRESULT(PARTYSEARCHRESULT pARTYSEARCHRESULT) {
        this.pARTYSEARCHRESULT = pARTYSEARCHRESULT;
    }

    public String getPERRORCD() {
        return pERRORCD;
    }

    public void setPERRORCD(String pERRORCD) {
        this.pERRORCD = pERRORCD;
    }

    public String getPERRORMSG() {
        return pERRORMSG;
    }

    public void setPERRORMSG(String pERRORMSG) {
        this.pERRORMSG = pERRORMSG;
    }

}
