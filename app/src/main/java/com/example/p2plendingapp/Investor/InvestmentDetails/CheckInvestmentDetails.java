package com.example.p2plendingapp.Investor.InvestmentDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p2plendingapp.Borrower.LoanDetails.DetailsFragment;
import com.example.p2plendingapp.Investor.MainDashboardInvestor;
import com.example.p2plendingapp.R;

public class CheckInvestmentDetails extends AppCompatActivity {

    FragmentManager manager;
    FragmentTransaction transaction;
    Intent sIntent;
    Button goBackInvestmentDBt;

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
}