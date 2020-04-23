package loanapp.udit.com.loanapplication.DataSync.SearchPartyService.OutputParameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutputParameterSearchParty {
    @SerializedName("OutputParameters")
    @Expose
    private OutputParameters outputParameters;

    public OutputParameters getOutputParameters() {
        return outputParameters;
    }

    public void setOutputParameters(OutputParameters outputParameters) {
        this.outputParameters = outputParameters;
    }

}

