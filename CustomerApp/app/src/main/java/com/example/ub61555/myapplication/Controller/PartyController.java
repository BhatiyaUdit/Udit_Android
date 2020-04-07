package com.example.ub61555.myapplication.Controller;

import com.example.ub61555.myapplication.Bean.PartyBean;
import com.example.ub61555.myapplication.Model.PartyModel;

import java.util.List;

public class PartyController {
    PartyModel partyModel;

    public void insertParty(PartyBean partyBean) {
        //Map Data here to database
        partyModel = new PartyModel();
        partyModel.setPartyName(partyBean.getPartyName());
        partyModel.setPartyDOB(partyBean.getPartyDOB());
        partyModel.insertParty();
    }

    public List<PartyBean> displayAllParties() {
        partyModel = new PartyModel();
        return partyModel.getAllParties();

    }

    public PartyBean getPartyById(Integer partyId) {
        partyModel = new PartyModel();
        return partyModel.getPartyById(partyId);
    }

    public void updateParty(PartyBean partyBean) {
        partyModel = new PartyModel();
        partyModel.setPartyName(partyBean.getPartyName());
        partyModel.setPartyId(partyBean.getPartyId());
        partyModel.setPartyDOB(partyBean.getPartyDOB());
        partyModel.updateParty();
    }

    public List<PartyBean> searchParty(String searchText) {
        partyModel = new PartyModel();
        return partyModel.searchParty(searchText);
    }

    public void deleteParty(Integer partyId) {
        partyModel = new PartyModel();
        partyModel.deleteParty(partyId);
    }
}
