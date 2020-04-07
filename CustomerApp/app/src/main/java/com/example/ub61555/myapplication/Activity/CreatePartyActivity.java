package com.example.ub61555.myapplication.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ub61555.myapplication.Bean.PartyBean;
import com.example.ub61555.myapplication.Controller.PartyController;
import com.example.ub61555.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreatePartyActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    PartyController controller;
    PartyBean partyBean;
    EditText partyNameET;
    EditText partyDOBET;
    Bundle partyBundle;
    Integer partyId;
    Boolean isUpdate =false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
        initializeDataObjects();
        populateDataFromDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initializeActivity(){
        setContentView(R.layout.add_party);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    private void initializeDataObjects(){
        controller = new PartyController();
        partyNameET = findViewById(R.id.editText);
        partyDOBET = findViewById(R.id.dateOfBirth);
        if (getIntent().getExtras() != null) {
            partyBundle = getIntent().getExtras();
        }
        if (partyBundle != null) {
            partyId = partyBundle.getInt("PARTY_ID");
            isUpdate = partyBundle.getBoolean("IS_UPDATE");
        }

    }

    private void populateDataFromDB() {
        if (isUpdate) {
            partyBean = controller.getPartyById(partyId);
            partyNameET.setText(partyBean.getPartyName());
            partyDOBET.setText(partyBean.getPartyDOB());
        }
    }

    public void populateDataToBean() {
        partyBean = new PartyBean();
        partyBean.setPartyName(partyNameET.getText().toString().trim());
        partyBean.setPartyId(partyId);
        partyBean.setPartyDOB(partyDOBET.getText().toString().trim());

    }

    public void onSaveClick(View view) {
        populateDataToBean();
        if(isUpdate){
            controller.updateParty(partyBean);
        }else{
            controller.insertParty(partyBean);
        }
        finish();
    }

    public void onCalenderClick(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePickerDialogTheme,this ,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
        datePickerDialog.setInverseBackgroundForced(false);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,dayOfMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String date = simpleDateFormat.format(cal.getTime());
        partyDOBET.setText(date);
    }
}
