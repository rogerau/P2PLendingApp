package com.example.p2plendingapp.General;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p2plendingapp.Borrower.MainDashboard;
import com.example.p2plendingapp.Investor.MainDashboardInvestor;
import com.example.p2plendingapp.R;

public class ProfileSelection extends AppCompatActivity implements View.OnClickListener{

    Button wantToBeBorrowerBt, wantToBeInvestorBt;
    Intent sIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_selection);
        wantToBeBorrowerBt = findViewById(R.id.wantToBeBorrowerBt);
        wantToBeInvestorBt = findViewById(R.id.wantToBeInvestorBt);

        //Set up listeners
        wantToBeBorrowerBt.setOnClickListener(this);
        wantToBeInvestorBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    if (v.getId() == R.id.wantToBeBorrowerBt) {
        openMainDashBoard("B");
    } else {
        openMainDashBoard("I");
    }

    }

    public void openMainDashBoard(String o) {
        if (o.equals("B")) {
            sIntent = new Intent(this, MainDashboard.class);
            startActivity(sIntent);
        } else {
            sIntent = new Intent(this, MainDashboardInvestor.class);
            startActivity(sIntent);
        }
    }
}