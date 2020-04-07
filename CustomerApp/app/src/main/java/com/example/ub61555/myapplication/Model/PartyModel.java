package com.example.ub61555.myapplication.Model;

import com.example.ub61555.myapplication.Bean.PartyBean;
import com.example.ub61555.myapplication.Broker.PartyBroker;

import java.util.List;

public class PartyModel {

    private Integer partyId;
    private String partyName;
    private PartyBroker partyBroker;
    private String partyDOB;

    public void insertParty(){
        partyBroker = new PartyBroker();
        partyBroker.insertParty(this);
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyDOB() {
        return partyDOB;
    }

    public void setPartyDOB(String partyDOB) {
        this.partyDOB = partyDOB;
    }

    public List<PartyBean> getAllParties() {
        partyBroker = new PartyBroker();
        return partyBroker.getAllPartiesFromDB();
    }

    public PartyBean getPartyById(Integer partyId) {
        partyBroker = new PartyBroker();
        return partyBroker.getPartyById(partyId);
    }

    public void updateParty() {
        partyBroker = new PartyBroker();
        partyBroker.updateParty(this);
    }

    public List<PartyBean> searchParty(String searchText) {
        partyBroker = new PartyBroker();
        return partyBroker.searchParty(searchText);
    }

    public void deleteParty(Integer partyId) {
        partyBroker = new PartyBroker();
        partyBroker.deleteParty(partyId);
    }
}
