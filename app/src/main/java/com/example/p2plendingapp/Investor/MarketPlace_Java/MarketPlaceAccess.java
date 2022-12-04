package com.example.p2plendingapp.Investor.MarketPlace_Java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.p2plendingapp.Database.p2pLendingDB;
import com.example.p2plendingapp.Investor.InvestmentDetails.CheckInvestmentDetails;
import com.example.p2plendingapp.Investor.MainDashboardInvestor;
import com.example.p2plendingapp.Model.Borrower;
import com.example.p2plendingapp.Model.Loan;
import com.example.p2plendingapp.Model.Opportunities;
import com.example.p2plendingapp.R;

import java.util.ArrayList;

public class MarketPlaceAccess extends AppCompatActivity {

    ListView listView;
    ListAdapterClass adapter;
    FragmentManager manager;
    Intent sIntent, lIntent;
    Button goBackMPButton;
    String pRLevel;
    Loan aLoan;
    Borrower aBorrower;
    p2pLendingDB db;
    int cId;
    Boolean agreedTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_place_access);
        listView = findViewById(R.id.iOpportunitiesList);

        goBackMPButton = findViewById(R.id.goBackMPButton);

        //Set the Adapter
        setAdapter();

        // Upon item click, checkbox will be set to checked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                populateAndGetList().get(position).setChecked(true);
                adapter.notifyDataSetChanged();
            }
        });

        //Set manager
        manager = getSupportFragmentManager();

        db = new p2pLendingDB(this);
        aLoan = new Loan();
        //Get data from investor application form
        getDataFromInvestorAppForm();

        //Set listeners
        goBackMPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sIntent = new Intent(getApplicationContext(), MainDashboardInvestor.class);
                //Send required data for the check investment details activity through the main investor dashboard
                //Continue passing the agreedTerms into the check investment details activity through the main investor dashboard
                sIntent.putExtra("agreed_terms", agreedTerms);
                //Continue passing the customerId into the check investment details activity through the main investor dashboard
                sIntent.putExtra("customer_id", cId);
                //Continue passing the risk level into the check investment details activity through the main investor dashboard
                sIntent.putExtra("preferred_risk_lvl", pRLevel);
                startActivity(sIntent);
            }
        });
    }


    public ArrayList<Opportunities> populateAndGetList() {
        //Lets read data from the loan table
//        ArrayList<String> aList = db.extractFromLoanTbForMPA();
//        double bAmount = Double.parseDouble(aList.subList(0, 0).toString());
//        int pPeriod = Integer.parseInt(aList.subList(1, 1).toString());

        //Lets read data from the investment table
//        ArrayList<String> aList2 = db.extractFromInvestmentTbForMPA();
//        double mEarnings = Double.parseDouble(aList2.subList(1, 1).toString());

        //Temporal
        double[] bAmount = {5000, 400, 6000, 1000, 6000};
        double[] mEarnings = {100, 200, 500, 50, 600};
        int[] pPeriod = {12, 24, 36, 12, 24};
        ArrayList<Opportunities> iOpportunities = new ArrayList<Opportunities>();
        for (int i = 0; i < bAmount.length; i++) {
//            iOpportunities.add(new Opportunities(i + 1, bAmount, mEarnings, pPeriod, aBorrower.setRiskLvl(), false));
            iOpportunities.add(new Opportunities(i + 1, bAmount[i], mEarnings[i], pPeriod[i], "High Risk", false));
        }
        return iOpportunities;
    }


    public void setAdapter() {
        //Setting the adapter
        adapter = new ListAdapterClass(this, populateAndGetList());
        listView.setAdapter(adapter);
    }

    public void showDealDialog(View view) {
        DealDialogFragment dealDialogFragment = new DealDialogFragment();
        manager = getSupportFragmentManager();
        dealDialogFragment.show(manager, "deal");

    }

    public void getDataFromInvestorAppForm() {
        lIntent = getIntent();
        pRLevel = lIntent.getStringExtra("preferred_risk_lvl");
        cId = lIntent.getIntExtra("customer_id", 0);
        agreedTerms = lIntent.getBooleanExtra("agreed_terms", true);
    }


}