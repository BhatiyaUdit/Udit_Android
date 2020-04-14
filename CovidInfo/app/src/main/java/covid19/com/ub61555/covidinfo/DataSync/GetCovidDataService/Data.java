package covid19.com.ub61555.covidinfo.DataSync.GetCovidDataService;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("paginationMeta")
    @Expose
    private PaginationMeta paginationMeta;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;
    @SerializedName("rows")
    @Expose
    private List<Row> rows = null;

    public PaginationMeta getPaginationMeta() {
        return paginationMeta;
    }

    public void setPaginationMeta(PaginationMeta paginationMeta) {
        this.paginationMeta = paginationMeta;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

}