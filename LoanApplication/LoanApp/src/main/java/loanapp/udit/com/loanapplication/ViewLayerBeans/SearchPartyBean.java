package loanapp.udit.com.loanapplication.ViewLayerBeans;

public class SearchPartyBean {
    private String kyc;
    private String partyFullName;
    private String partyContact;
    private String dateOfBirth;
    private Long prtyId;

    public String getKyc() {
        return kyc;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
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

    public Long getPrtyId() {
        return prtyId;
    }

    public void setPrtyId(Long prtyId) {
        this.prtyId = prtyId;
    }
}
