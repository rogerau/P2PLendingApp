package com.example.p2plendingapp.Investor.MarketPlace_Java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.p2plendingapp.Investor.InvestmentDetails.CheckInvestmentDetails;
import com.example.p2plendingapp.Investor.MainDashboardInvestor;
import com.example.p2plendingapp.Model.Opportunities;
import com.example.p2plendingapp.R;

import java.util.ArrayList;

public class MarketPlaceAccess extends AppCompatActivity {

    ListView listView;
    ListAdapterClass adapter;
    FragmentManager manager;
    Intent sIntent, lIntent;
    Button goBackMPButton;

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

        //Set listeners
        goBackMPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainDashboardInvestor.class);
                startActivity(intent);
            }
        });
    }


    public ArrayList<Opportunities> populateAndGetList() {
        ArrayList<Opportunities> iOpportunities = new ArrayList<Opportunities>();
        iOpportunities.add(new Opportunities(1, "3300", 1000, 6, "High Risk", 100, false));
        iOpportunities.add(new Opportunities(2, "3300", 1000, 6, "High Risk", 100, false));
        iOpportunities.add(new Opportunities(3, "3300", 1000, 6, "High Risk", 100, true));
        iOpportunities.add(new Opportunities(4, "3300", 1000, 6, "High Risk", 100, false));
        iOpportunities.add(new Opportunities(5, "3300", 1000, 6, "High Risk", 100, true));
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
        lIntent.getStringExtra("preferred_risk_lvl");
        lIntent.getStringExtra("customer_id");
        lIntent.getStringExtra("agreed_terms");


    }


}