package com.example.ub61555.myapplication.Bean;


public class PartyBean {
    private Integer partyId;
    private String partyName;
    private String partyDOB;

    public PartyBean(String partyName) {
        this.partyName = partyName;
    }

    public PartyBean() {
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
}
