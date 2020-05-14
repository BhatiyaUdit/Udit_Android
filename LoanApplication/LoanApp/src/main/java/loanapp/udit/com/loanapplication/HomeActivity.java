package loanapp.udit.com.loanapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import loanapp.udit.com.loanapplication.Activity.PartyDetailActivity;
import loanapp.udit.com.loanapplication.Adaptor.CustomFragmentPagerAdaptor;
import loanapp.udit.com.loanapplication.Fragment.DummyFragment;
import loanapp.udit.com.loanapplication.Fragment.SearchCustomerFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager homePager = findViewById(R.id.HomeViewPager);
        setupViewPager(homePager);
        TabLayout tabLayout = findViewById(R.id.homeTabs);
        tabLayout.setupWithViewPager(homePager);
    }

    private void setupViewPager(ViewPager viewPager) {
        CustomFragmentPagerAdaptor adapter = new CustomFragmentPagerAdaptor(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFrag(new SearchCustomerFragment(), "Party");
        adapter.addFrag(new DummyFragment(
                ContextCompat.getColor(this, R.color.colorAccent)), "Credit App");

        adapter.addFrag(new DummyFragment(
                ContextCompat.getColor(this, R.color.colorPrimaryDark)), "Quotation");
        viewPager.setAdapter(adapter);
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
              /*  SearchCustomerFragment searchCustomerFragment = (SearchCustomerFragment) getSupportFragmentManager().findFragmentById(R.layout.fragment_search_customer);
                searchCustomerFragment.loadData(newText,0);*/
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent mainActivity = new Intent(HomeActivity.this.getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            return true;
        }
        if (id == R.id.SearchParty) {

        }
        return super.onOptionsItemSelected(item);
    }

}
