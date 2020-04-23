package loanapp.udit.com.loanapplication.DataSync.SearchPartyService.InputParameter;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InputParameters {

    @SerializedName("P_USER_ID")
    @Expose
    private String pUSERID;
    @SerializedName("P_GEO_CNTRY_ID")
    @Expose
    private Integer pGEOCNTRYID;
    @SerializedName("SEARCH_PARAM")
    @Expose
    private List<SEARCHPARAM> sEARCHPARAM = null;

    public String getPUSERID() {
        return pUSERID;
    }

    public void setPUSERID(String pUSERID) {
        this.pUSERID = pUSERID;
    }

    public Integer getPGEOCNTRYID() {
        return pGEOCNTRYID;
    }

    public void setPGEOCNTRYID(Integer pGEOCNTRYID) {
        this.pGEOCNTRYID = pGEOCNTRYID;
    }

    public List<SEARCHPARAM> getSEARCHPARAM() {
        return sEARCHPARAM;
    }

    public void setSEARCHPARAM(List<SEARCHPARAM> sEARCHPARAM) {
        this.sEARCHPARAM = sEARCHPARAM;
    }

}