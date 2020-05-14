package loanapp.udit.com.loanapplication.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import loanapp.udit.com.loanapplication.Activity.PartyDetailActivity;
import loanapp.udit.com.loanapplication.Adaptor.CustomSearchPartyAdaptor;
import loanapp.udit.com.loanapplication.CommonApplicationUtil;
import loanapp.udit.com.loanapplication.Controller.PartyController;
import loanapp.udit.com.loanapplication.DataSync.PartyAsyncTask;
import loanapp.udit.com.loanapplication.DataSync.PartyAsyncTaskInterface;
import loanapp.udit.com.loanapplication.DataSync.SearchPartyService.SearchPartyDataProcessor.SearchPartyServiceDataProcessor;
import loanapp.udit.com.loanapplication.MainActivity;
import loanapp.udit.com.loanapplication.R;
import loanapp.udit.com.loanapplication.ViewLayerBeans.SearchPartyBean;

public class SearchCustomerFragment extends Fragment implements PartyAsyncTaskInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FloatingActionButton fab;
    ListView PartyListLV;
    PartyController partyController;
    SwipeRefreshLayout srlPartySearch;
    BottomAppBar bar;
    public static int sortBy=0;
    Button sortButton;

    public SearchCustomerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchCustomerFragment newInstance(String param1, String param2) {
        SearchCustomerFragment fragment = new SearchCustomerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_customer, container, false);
        initializeActivity(rootView);
        initializeDataObjects();
        loadData(sortBy);
        return rootView;

    }

    private void initializeActivity(View view) {
        fab= (FloatingActionButton) view.findViewById(R.id.fab);
        PartyListLV =view.findViewById(R.id.partyList);
        srlPartySearch = view.findViewById(R.id.SwipeLayout);
        bar = (BottomAppBar) view.findViewById(R.id.bar);
        partyController = new PartyController();
        sortButton = view.findViewById(R.id.sortButton);
    }

    private void initializeDataObjects() {
        srlPartySearch.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromService();
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSortClick(v);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tabactivity = new Intent(getActivity().getApplicationContext(), PartyDetailActivity.class);
                startActivity(tabactivity);
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

    private void populateListAdaptor(ArrayList<SearchPartyBean> searchPartyList ){
        CustomSearchPartyAdaptor adaptor = new CustomSearchPartyAdaptor(getActivity().getApplicationContext(),R.layout.party_list,searchPartyList);
        PartyListLV.setAdapter(adaptor);
        PartyListLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "HAHA"+position,Toast.LENGTH_SHORT).show();
                Intent tabactivity = new Intent(getActivity().getApplicationContext(), PartyDetailActivity.class);
                startActivity(tabactivity);
            }
        });
    }


    @Override
    public void getDataPostExecute(String outputJSON) {
        SearchPartyServiceDataProcessor dataProcessor =  new SearchPartyServiceDataProcessor(outputJSON);
        dataProcessor.processResponse();
        loadData(sortBy);
        srlPartySearch.setRefreshing(false);
        if(!CommonApplicationUtil.ErrorMessage.equals("")){
            Snackbar.make(getActivity().findViewById(R.id.SwipeLayout), CommonApplicationUtil.ErrorMessage, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
    }

    public void onSortClick(View view) {
        String[] singleItems = new String[]{"Name - Ascending", "Name - Descending", "Creation Date - Latest First", "Creation Date - Oldest First"};
        int checkedItem = sortBy;

        new MaterialAlertDialogBuilder(getContext())
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

    public void loadData(String searchText, int sortByItem) {
        ArrayList<SearchPartyBean> searchPartyList  = new ArrayList<>();
        searchPartyList = partyController.getAllParties(searchText, sortByItem);
        populateListAdaptor(searchPartyList);
    }

}
