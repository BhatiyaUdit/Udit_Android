package loanapp.udit.com.loanapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import loanapp.udit.com.loanapplication.Activity.PartyDetailActivity;
import loanapp.udit.com.loanapplication.Adaptor.CustomSearchPartyAdaptor;
import loanapp.udit.com.loanapplication.Controller.PartyController;
import loanapp.udit.com.loanapplication.DataSync.PartyAsyncTask;
import loanapp.udit.com.loanapplication.DataSync.PartyAsyncTaskInterface;
import loanapp.udit.com.loanapplication.DataSync.SearchPartyService.SearchPartyDataProcessor.SearchPartyServiceDataProcessor;
import loanapp.udit.com.loanapplication.ViewLayerBeans.SearchPartyBean;

public class MainActivity extends AppCompatActivity implements PartyAsyncTaskInterface {

    Toolbar toolbar;
    FloatingActionButton fab;
    ListView PartyListLV;
    PartyController partyController;
    SwipeRefreshLayout srlPartySearch;
    BottomAppBar bar;
    public static int sortBy=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
        initializeDataObjects();
        loadData(sortBy);
    }

    private void initializeActivity() {
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab= (FloatingActionButton) findViewById(R.id.fab);
        PartyListLV = findViewById(R.id.partyList);
        srlPartySearch = findViewById(R.id.SwipeLayout);
        bar = (BottomAppBar) findViewById(R.id.bar);

        partyController = new PartyController();

    }

    private void initializeDataObjects() {
        srlPartySearch.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromService();
            }
        });


    }

    private void getDataFromService() {
        PartyAsyncTask partyAsyncTask = new PartyAsyncTask();
        partyAsyncTask.partyAsyncTaskInterface = this;
        partyAsyncTask.execute(CommonApplicationUtil.BaseUrl+SEARCH_PARTY_URL);
    }

    private void loadData(int itemClicked) {
        ArrayList<SearchPartyBean> searchPartyList  = new ArrayList<>();
        searchPartyList = partyController.getAllParties(itemClicked);
        populateListAdaptor(searchPartyList);
    }

    private void loadData(String searchText, int sortByItem) {
        ArrayList<SearchPartyBean> searchPartyList  = new ArrayList<>();
        searchPartyList = partyController.getAllParties(searchText, sortByItem);
        populateListAdaptor(searchPartyList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchBar = menu.findItem(R.id.SearchParty);
        SearchView searchView = (SearchView) searchBar.getActionView();
        searchView.setQueryHint("Type Name, Address or KYC...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadData(newText, sortBy);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent tabactivity = new Intent(MainActivity.this.getApplicationContext(), PartyDetailActivity.class);
            //startActivity(tabactivity);
            return true;
        }
        if (id == R.id.SearchParty) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getDataPostExecute(String outputJSON) {
        SearchPartyServiceDataProcessor dataProcessor =  new SearchPartyServiceDataProcessor(outputJSON);
        dataProcessor.processResponse();
        loadData(sortBy);
        srlPartySearch.setRefreshing(false);
        if(!CommonApplicationUtil.ErrorMessage.equals("")){
            Snackbar.make(findViewById(R.id.SwipeLayout), CommonApplicationUtil.ErrorMessage, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }
    }

    public void onSortClick(View view) {
        String[] singleItems = new String[]{"Name - Ascending", "Name - Descending", "Creation Date - Latest First", "Creation Date - Oldest First"};
        int checkedItem = sortBy;

        new MaterialAlertDialogBuilder(this)
                .setTitle("Sort By")
                .setSingleChoiceItems(singleItems, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sortBy = which;
                        loadData(sortBy);
                        dialog.dismiss();
                    }
                })
                .show();

    }

    public void onFilterClick(View view) {
        String[] multiItems = new String[]{"Individual", "Organization"};
        boolean[] checkedItem = {true,false};

        new MaterialAlertDialogBuilder(this)
                .setTitle("Filter By")
                .setMultiChoiceItems(multiItems, checkedItem, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                })
                .show();
    }

    private void populateListAdaptor(ArrayList<SearchPartyBean> searchPartyList ){
        CustomSearchPartyAdaptor adaptor = new CustomSearchPartyAdaptor(this,R.layout.party_list,searchPartyList);
        PartyListLV.setAdapter(adaptor);
        PartyListLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this.getApplicationContext(), "HAHA"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
