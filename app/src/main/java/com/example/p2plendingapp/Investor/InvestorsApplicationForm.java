package com.example.p2plendingapp.Investor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.p2plendingapp.Database.p2pLendingDB;
import com.example.p2plendingapp.Investor.MarketPlace_Java.MarketPlaceAccess;
import com.example.p2plendingapp.Model.Investor;
import com.example.p2plendingapp.R;

public class InvestorsApplicationForm extends AppCompatActivity implements View.OnClickListener {

    Button submitIAppFormButton, declineIAppFormButton;
    Intent sIntent, lIntent;
    p2pLendingDB db;
    Investor anInvestor;
    RadioGroup cRLIAppForm;
    EditText cBankAccIAppFormEt, sFundsIAppFormEt;
    int randomCId, pRiskLevelId;
    String canadianBankAcc, sOFunds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investors_application_form);

        submitIAppFormButton = findViewById(R.id.submitIAppFormButton);
        declineIAppFormButton = findViewById(R.id.declineIAppFormButton);
        cRLIAppForm = findViewById(R.id.cRLIAppForm);
        cBankAccIAppFormEt = findViewById(R.id.cBankAccIAppFormEt);
        sFundsIAppFormEt = findViewById(R.id.sFundsIAppFormEt);

        //Set up listeners
        submitIAppFormButton.setOnClickListener(this);
        declineIAppFormButton.setOnClickListener(this);

        db = new p2pLendingDB(this);
        anInvestor = new Investor();
    }

    public void exploreMarketPlace() {
        lIntent = getIntent();
        //Receive the agreedTerms from agree general conditions activity
        Boolean agreedTerms = lIntent.getBooleanExtra("Agreed Terms", true);

        //Receive the customerId from agree general conditions activity

        sIntent = new Intent(this, MarketPlaceAccess.class);

        //Continue passing the agreedTerms into the MarketPlaceAccess activity
        sIntent.putExtra("agreed_terms", agreedTerms);

        //Continue passing the customerId into the MarketPlaceAccess activity
        //Generate a random integer 4 digits as a lId from 1000 to 9999 (temporal)
        int min = 1000;
        int max = 9999;
        randomCId = (int) Math.floor(Math.random() * (max - min + 1) + min);
        sIntent.putExtra("customer_id", randomCId);

        //Validate inputs
        canadianBankAcc = cBankAccIAppFormEt.getText().toString();
        if (canadianBankAcc.isEmpty()) {
            Toast.makeText(this, "Please, insert a canadian bank account", Toast.LENGTH_SHORT).show();
        } else {
            sOFunds = sFundsIAppFormEt.getText().toString();
            if (sOFunds.isEmpty()) {
                Toast.makeText(this, "Please, insert a source of funds", Toast.LENGTH_SHORT).show();
            } else {
                //Get the id of every radio button inside choose risk level radio group
                pRiskLevelId = cRLIAppForm.getCheckedRadioButtonId();
                if (pRiskLevelId == -1) {
                    Toast.makeText(this, "Please, you must check an option in risk level preference", Toast.LENGTH_SHORT).show();
                } else {
                    //Pass the following data to the MarketPlaceAccess activity
                    if (pRiskLevelId == R.id.highRiskIAppForm) {
                        sIntent.putExtra("preferred_risk_lvl", R.string.high_risk);
                    } else if (pRiskLevelId == R.id.moHighRiskIAppForm) {
                        sIntent.putExtra("preferred_risk_lvl", R.string.moderate_high_risk);
                    } else if (pRiskLevelId == R.id.lHighRiskIAppForm) {
                        sIntent.putExtra("preferred_risk_lvl", R.string.low_high_risk);
                    } else if (pRiskLevelId == R.id.mediumRiskIAppForm) {
                        sIntent.putExtra("preferred_risk_lvl", R.string.medium_risk);
                    } else if (pRiskLevelId == R.id.moMediumRiskIAppForm) {
                        sIntent.putExtra("preferred_risk_lvl", R.string.moderate_medium_risk);
                    } else if (pRiskLevelId == R.id.lMediumRiskIAppForm) {
                        sIntent.putExtra("preferred_risk_lvl", R.string.low_medium_risk);
                    } else if (pRiskLevelId == R.id.lowRiskIAppForm) {
                        sIntent.putExtra("preferred_risk_lvl", R.string.low_risk);
                    } else if (pRiskLevelId == R.id.moLowRiskIAppForm) {
                        sIntent.putExtra("preferred_risk_lvl", R.string.moderate_low_risk);
                    } else {
                        sIntent.putExtra("preferred_risk_lvl", R.string.lower_low_risk);
                    }
                    startActivity(sIntent);
                    //Store investor data into the investor table
                    storeInvestorDataIntoInvestorTable();
                }
            }

        }

    }

    public void storeInvestorDataIntoInvestorTable() {
        //Here you must store the cId into the investor table.
        anInvestor.setInId(randomCId);
        anInvestor.setsOFunds(sOFunds);
        anInvestor.setcBAccount(canadianBankAcc);
        //Get the id of every radio button inside residency status radio group
        //Assign the respective value to the Borrower object
        if (pRiskLevelId == R.id.highRiskIAppForm) {
            anInvestor.setpRLevel(String.valueOf(R.string.high_risk));
        } else if (pRiskLevelId == R.id.moHighRiskIAppForm) {
            anInvestor.setpRLevel(String.valueOf(R.string.moderate_high_risk));
        } else if (pRiskLevelId == R.id.lHighRiskIAppForm) {
            anInvestor.setpRLevel(String.valueOf(R.string.low_high_risk));
        } else if (pRiskLevelId == R.id.mediumRiskIAppForm) {
            anInvestor.setpRLevel(String.valueOf(R.string.medium_risk));
        } else if (pRiskLevelId == R.id.moMediumRiskIAppForm) {
            anInvestor.setpRLevel(String.valueOf(R.string.moderate_medium_risk));
        } else if (pRiskLevelId == R.id.lMediumRiskIAppForm) {
            anInvestor.setpRLevel(String.valueOf(R.string.low_medium_risk));
        } else if (pRiskLevelId == R.id.lowRiskIAppForm) {
            anInvestor.setpRLevel(String.valueOf(R.string.low_risk));
        } else if (pRiskLevelId == R.id.moLowRiskIAppForm) {
            anInvestor.setpRLevel(String.valueOf(R.string.moderate_low_risk));
        } else {
            anInvestor.setpRLevel(String.valueOf(R.string.lower_low_risk));
        }

        //Insert the data into the Investors table
        long numInserted = db.insertIntoInvestorTb(anInvestor);
        Toast.makeText(this, numInserted + " row(s) were inserted!", Toast.LENGTH_SHORT).show();
    }

    public void returnToMainDashBoardI() {
        sIntent = new Intent(getApplicationContext(), MainDashboardInvestor.class);
        startActivity(sIntent);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.declineIAppFormButton) {
            returnToMainDashBoardI();
        } else {
            //Display explore market place activity
            exploreMarketPlace();
        }
    }
}