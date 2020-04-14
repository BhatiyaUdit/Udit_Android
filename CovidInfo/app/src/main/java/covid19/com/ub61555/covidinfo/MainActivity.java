package covid19.com.ub61555.covidinfo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import covid19.com.ub61555.covidinfo.DataSync.AsyncTaskInterface;
import covid19.com.ub61555.covidinfo.DataSync.DataAsyncTask;
import covid19.com.ub61555.covidinfo.DataSync.DataUrlInterface;
import covid19.com.ub61555.covidinfo.DataSync.GetCovidDataService.CovidData;

public class MainActivity extends AppCompatActivity implements AsyncTaskInterface{

    ListView country_data_lv;
    DataAsyncTask dataAsyncTask;
    ArrayList<CovidDataBean> covidDataBeans;
    SwipeRefreshLayout refreshLayout;
    RelativeLayout relativeLayout;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
        populateData();
    }

    private void initializeActivity() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_corona);

        country_data_lv =  findViewById(R.id.CountriesList);
        refreshLayout = findViewById(R.id.swipe);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        relativeLayout= findViewById(R.id.progressBarlayout);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateData();
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateData();
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });

        relativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchIcon =  menu.findItem(R.id.search_country);
        SearchView searchView = (SearchView) searchIcon.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                relativeLayout.setVisibility(View.VISIBLE);
                populateData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                /*Snackbar.make(findViewById(R.id.swipe), "Selected Country......" +newText, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                relativeLayout.setVisibility(View.VISIBLE);
                populateData(newText);*/
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void populateData() {
        dataAsyncTask= new DataAsyncTask(this, DataUrlInterface.COVID_ALL_DATA_URL+"?limit=100");
        dataAsyncTask.taskInterface = this;
        dataAsyncTask.execute();
    }

    private void populateData(String newText) {
        dataAsyncTask= new DataAsyncTask(this, DataUrlInterface.COVID_ALL_DATA_URL+"?search="+newText);
        dataAsyncTask.taskInterface = this;
        dataAsyncTask.execute();
    }



    @Override
    public void getDataPostExecute(String output) {
        CovidData covidData = new CovidData();
        Gson gson = new Gson();
        covidData = gson.fromJson(output,CovidData.class);

        covidDataBeans  = new ArrayList<>();
        if(covidData!=null && covidData.getData()!=null && covidData.getData().getRows()!=null){
            for (int i = 0; i< covidData.getData().getRows().size();i++){
                CovidDataBean dataBean = new CovidDataBean();
                dataBean.setCountry(covidData.getData().getRows().get(i).getCountry());
                dataBean.setTotalNewCases(Long.parseLong(covidData.getData().getRows().get(i).getNewCases().replace(",","")));
                dataBean.setTotalCases(Long.parseLong(covidData.getData().getRows().get(i).getTotalCases().replace(",","")));
                dataBean.setTotalRecovered(Long.parseLong(covidData.getData().getRows().get(i).getTotalRecovered().replace(",","")));
                dataBean.setTotalDeaths(Long.parseLong(covidData.getData().getRows().get(i).getTotalDeaths().replace(",","")));
                dataBean.setFlagUrl(covidData.getData().getRows().get(i).getFlag());
                covidDataBeans.add(dataBean);
            }
            CustomCovidDataAdaptor covidDataAdaptor = new CustomCovidDataAdaptor(this, R.layout.country_data_list_item, covidDataBeans);
            country_data_lv.setAdapter(covidDataAdaptor);
            country_data_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Snackbar.make(view, "Selected Country......" + covidDataBeans.get(position).getCountry(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }else {
            Snackbar.make(findViewById(R.id.swipe), "Error Loading Data... Please try again" , Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        relativeLayout.setVisibility(View.INVISIBLE);
        refreshLayout.setRefreshing(false);
    }

}
