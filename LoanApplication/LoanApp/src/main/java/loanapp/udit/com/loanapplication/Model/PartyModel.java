package loanapp.udit.com.loanapplication.Model;

import java.util.ArrayList;

import loanapp.udit.com.loanapplication.Broker.PartyBroker;
import loanapp.udit.com.loanapplication.ViewLayerBeans.SearchPartyBean;
// This class will act as Database Model includes beans and functions
public class PartyModel {
    private Long prtyId;
    private String kyc;
    private Integer prtyCtgryTypId;
    private String partyFullName;
    private String partyContact;
    private String dateOfBirth;
    private String geoRegion;
    private String createdBy;
    private String addrLineTxt;
    PartyBroker partyBroker;


    public ArrayList<SearchPartyBean> getAllParties() {
        partyBroker = new PartyBroker();
        return partyBroker.getAllParties();
    }

    public void insertPartyList(ArrayList<SearchPartyBean> searchPartyList) {
        partyBroker = new PartyBroker();
        partyBroker.insertPartyList(searchPartyList);
    }
}
