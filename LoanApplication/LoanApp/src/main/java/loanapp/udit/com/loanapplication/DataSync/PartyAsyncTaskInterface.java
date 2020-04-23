package loanapp.udit.com.loanapplication.DataSync;

public interface PartyAsyncTaskInterface {
    public String SEARCH_PARTY_URL ="https://servicesextqual.tal.deere.com/cacd/search_party/";
    public void getDataPostExecute(String outputJSON);
}
