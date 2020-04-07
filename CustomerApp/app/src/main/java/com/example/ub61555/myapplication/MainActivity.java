package com.example.ub61555.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.example.ub61555.myapplication.Activity.CreatePartyActivity;
import com.example.ub61555.myapplication.Adaptor.CustomPartyListAdaptor;
import com.example.ub61555.myapplication.Bean.PartyBean;
import com.example.ub61555.myapplication.Controller.PartyController;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PartyController partyController;
    ListView partyList;
    CustomPartyListAdaptor arrayAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
        initializeDataObjects();
        displayAllParties();
        //comment added

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchBar = menu.findItem(R.id.SearchIcon);
        SearchView searchView = (SearchView) searchBar.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<PartyBean> partyList1 = partyController.searchParty(newText);
                arrayAdapter= new CustomPartyListAdaptor(MainActivity.this.getApplicationContext(), R.layout.party_list, partyList1);
                partyList.setAdapter(arrayAdapter);
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
        if (id == R.id.AddDummy) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayAllParties();
    }

    public void displayAllParties() {
        final List<PartyBean> partyList1 = partyController.displayAllParties();
        arrayAdapter= new CustomPartyListAdaptor(this, R.layout.party_list, partyList1);
        partyList.setAdapter(arrayAdapter);
        partyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                 Intent createPartyIntent  = new Intent(MainActivity.this.getApplicationContext(), CreatePartyActivity.class);
                                                 Bundle partyBundle = new Bundle();
                                                 partyBundle.putInt("PARTY_ID", partyList1.get(position).getPartyId());
                                                 partyBundle.putBoolean("IS_UPDATE", true);
                                                 createPartyIntent.putExtras(partyBundle);
                                                 startActivity(createPartyIntent);

                                             }
                                         }

        );
    }


    private void initializeDataObjects() {
        partyController = new PartyController();
        partyList = findViewById(R.id.PartyList);
    }

    private void initializeActivity() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this.getApplicationContext(), CreatePartyActivity.class);
                startActivity(i);
            }
        });
    }

    public void onPartyImageClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

}
