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

    public ArrayList<SearchPartyBean> getAllParties(String searchText,int sortBy) {
        partyModel = new PartyModel();
        return partyModel.getAllParties(searchText,sortBy);
    }

    public ArrayList<SearchPartyBean> getAllParties(int sortBy) {
        partyModel = new PartyModel();
        return partyModel.getAllParties(sortBy);
    }
}
