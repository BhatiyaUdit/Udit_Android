package loanapp.udit.com.loanapplication.DataSync.SearchPartyService.OutputParameter;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PARTYSEARCHRESULT {

    @SerializedName("PARTY_SEARCH_RESULT_ITEM")
    @Expose
    private List<PARTYSEARCHRESULTITEM> pARTYSEARCHRESULTITEM = null;

    public List<PARTYSEARCHRESULTITEM> getPARTYSEARCHRESULTITEM() {
        return pARTYSEARCHRESULTITEM;
    }

    public void setPARTYSEARCHRESULTITEM(List<PARTYSEARCHRESULTITEM> pARTYSEARCHRESULTITEM) {
        this.pARTYSEARCHRESULTITEM = pARTYSEARCHRESULTITEM;
    }

}