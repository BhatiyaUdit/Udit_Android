package loanapp.udit.com.loanapplication.DataSync.SearchPartyService.SearchPartyDataProcessor;

import com.google.gson.Gson;

import java.util.ArrayList;

import loanapp.udit.com.loanapplication.DataSync.SearchPartyService.OutputParameter.OutputParameterSearchParty;
import loanapp.udit.com.loanapplication.Model.PartyModel;

public class SearchPartyServiceDataProcessor {

    private String searchPartyResponse;
    private PartyModel partyModel;

    public SearchPartyServiceDataProcessor(String ResponseJSON) {
        searchPartyResponse= ResponseJSON;
        partyModel = new PartyModel();
    }

    public String processResponse(){
        Gson gson = new Gson();
        ArrayList<PartyModel> searchPartyList  = new ArrayList<>();
        OutputParameterSearchParty outputParameterSearchParty = gson.fromJson(searchPartyResponse, OutputParameterSearchParty.class);
        if(outputParameterSearchParty!=null && outputParameterSearchParty.getOutputParameters()!=null && outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT()!=null &&
                outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().size()>0

        ){
            for(int i= 0 ; i < outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().size() ; i++){
                PartyModel partyBean = new PartyModel();
                partyBean.setPrtyId(Long.parseLong(outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().get(i).getPRTYID()));
                partyBean.setKyc(outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().get(i).getKYC());
                partyBean.setPrtyCtgryTypId(Integer.parseInt(outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().get(i).getPRTYCTGRYTYPID()));
                partyBean.setPartyFullName(outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().get(i).getPARTYFULLNAME());
                partyBean.setPartyContact(outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().get(i).getPARTYCONTACT());
                partyBean.setDateOfBirth(outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().get(i).getINDVBIRTHDT());
                partyBean.setGeoRegion(outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().get(i).getGEOREGNNM());
                partyBean.setCreatedBy(outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().get(i).getCREATEDBY());
                partyBean.setAddrLineTxt(outputParameterSearchParty.getOutputParameters().getPARTYSEARCHRESULT().getPARTYSEARCHRESULTITEM().get(i).getADDRLINE1TXT());
                searchPartyList.add(partyBean);
            }
        }

        if(searchPartyList.size()>0){
            partyModel.insertPartyList(searchPartyList);
        }
        return null;
    }
}
