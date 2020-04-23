package loanapp.udit.com.loanapplication.Controller;

import java.util.ArrayList;

import loanapp.udit.com.loanapplication.Model.PartyModel;
import loanapp.udit.com.loanapplication.ViewLayerBeans.SearchPartyBean;
// This class is used for mapping data between view layer and database layer
public class PartyController {
    PartyModel partyModel;

    public ArrayList<SearchPartyBean> getAllParties() {
        partyModel = new PartyModel();
        return partyModel.getAllParties();
    }

    public void insertPartyList(ArrayList<SearchPartyBean> searchPartyList) {
        partyModel = new PartyModel();
        partyModel.insertPartyList(searchPartyList);
    }
}
