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

    public Long getPrtyId() {
        return prtyId;
    }

    public void setPrtyId(Long prtyId) {
        this.prtyId = prtyId;
    }

    public String getKyc() {
        return kyc;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
    }

    public Integer getPrtyCtgryTypId() {
        return prtyCtgryTypId;
    }

    public void setPrtyCtgryTypId(Integer prtyCtgryTypId) {
        this.prtyCtgryTypId = prtyCtgryTypId;
    }

    public String getPartyFullName() {
        return partyFullName;
    }

    public void setPartyFullName(String partyFullName) {
        this.partyFullName = partyFullName;
    }

    public String getPartyContact() {
        return partyContact;
    }

    public void setPartyContact(String partyContact) {
        this.partyContact = partyContact;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGeoRegion() {
        return geoRegion;
    }

    public void setGeoRegion(String geoRegion) {
        this.geoRegion = geoRegion;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAddrLineTxt() {
        return addrLineTxt;
    }

    public void setAddrLineTxt(String addrLineTxt) {
        this.addrLineTxt = addrLineTxt;
    }

    public ArrayList<SearchPartyBean> getAllParties() {
        partyBroker = new PartyBroker();
        return partyBroker.getAllParties();
    }

    public void insertPartyList(ArrayList<PartyModel> searchPartyList) {
        partyBroker = new PartyBroker();
        partyBroker.insertPartyList(searchPartyList);
    }

    public ArrayList<SearchPartyBean> getAllParties(String searchText,int sortBy) {
        partyBroker = new PartyBroker();
        return partyBroker.getAllParties(searchText, sortBy);
    }

    public ArrayList<SearchPartyBean> getAllParties(int sortBy) {
        partyBroker = new PartyBroker();
        return partyBroker.getAllParties(sortBy);
    }
}
