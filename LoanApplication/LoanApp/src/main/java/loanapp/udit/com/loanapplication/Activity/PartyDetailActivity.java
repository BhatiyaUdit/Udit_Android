package loanapp.udit.com.loanapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import loanapp.udit.com.loanapplication.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputLayout;

public class PartyDetailActivity extends AppCompatActivity {

    TextInputLayout partyNameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);
        Toolbar partyDetailToolBar = findViewById(R.id.party_detail_toolbar);
        setSupportActionBar(partyDetailToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        partyNameLayout = findViewById(R.id.PBDpartyName);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
