package com.example.p2plendingapp.Investor.InvestmentDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.p2plendingapp.Borrower.LoanDetails.DetailsFragment;
import com.example.p2plendingapp.Database.p2pLendingDB;
import com.example.p2plendingapp.Investor.MainDashboardInvestor;
import com.example.p2plendingapp.Model.Investment;
import com.example.p2plendingapp.R;

public class CheckInvestmentDetails extends AppCompatActivity {

    FragmentManager manager;
    FragmentTransaction transaction;
    Intent sIntent, lIntent;
    Button goBackInvestmentDBt;
    String pRLevel;
    int cId, randomIiD;
    Boolean aTerms;
    Investment anInvestment;
    p2pLendingDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_investment_details);
        manager = getSupportFragmentManager();

        goBackInvestmentDBt = findViewById(R.id.goBackInvestmentDBt);

        //Set up listener
        goBackInvestmentDBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sIntent = new Intent(getApplicationContext(), MainDashboardInvestor.class);
                startActivity(sIntent);
            }
        });
        anInvestment = new Investment();
        db = new p2pLendingDB(this);
        //Get data from main dashboard investor
        getDataFromMainDashBoardI();
        //Store data into investment table
        storeDataIntoInvestmentTb();
    }

    public void addIDetailFragment(View view) {
        IDetailsFragment iDetailsFragment = new IDetailsFragment();
        transaction = manager.beginTransaction();
        transaction.add(R.id.detailsContainerInvestmentD, iDetailsFragment, "i_details_fragment");
        transaction.commit();
    }

    public void removeIDetailFragment(View view) {
        IDetailsFragment fragment = (IDetailsFragment) manager.findFragmentByTag("i_details_fragment");
        transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }


    public void getDataFromMainDashBoardI() {
        lIntent = getIntent();
        cId = lIntent.getIntExtra("customer_id", 0);
        pRLevel = lIntent.getStringExtra("preferred_risk_lvl");
        aTerms = lIntent.getBooleanExtra("agreed_terms", true);
    }

    public void storeDataIntoInvestmentTb() {
        //Generate a random integer 5 digits as a iId from 10000 to 99999 (temporal)
        int min = 10000;
        int max = 99999;
        randomIiD = (int) Math.floor(Math.random() * (max - min + 1) + min);
        anInvestment = new Investment(randomIiD, pRLevel, false, aTerms, 0, cId);
        //Insert the data into the Investors table
        long numInserted = db.insertIntoInvestmentTb(anInvestment);
        Toast.makeText(this, numInserted + " row(s) were inserted!", Toast.LENGTH_SHORT).show();
    }
}