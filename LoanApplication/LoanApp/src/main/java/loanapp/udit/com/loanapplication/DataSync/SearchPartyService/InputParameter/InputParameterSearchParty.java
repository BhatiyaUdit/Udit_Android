package loanapp.udit.com.loanapplication.DataSync.SearchPartyService.InputParameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InputParameterSearchParty {

    @SerializedName("InputParameters")
    @Expose
    private InputParameters inputParameters;

    public InputParameters getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(InputParameters inputParameters) {
        this.inputParameters = inputParameters;
    }

}
