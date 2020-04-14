package covid19.com.ub61555.covidinfo;

public class CovidDataBean {

    private String country;
    private Long totalCases;
    private Long totalDeaths;
    private Long totalNewCases;
    private Long totalRecovered;
    private String flagUrl;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Long totalCases) {
        this.totalCases = totalCases;
    }

    public Long getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Long totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Long getTotalNewCases() {
        return totalNewCases;
    }

    public void setTotalNewCases(Long totalNewCases) {
        this.totalNewCases = totalNewCases;
    }

    public Long getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(Long totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
}
