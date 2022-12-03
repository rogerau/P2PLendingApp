package com.example.p2plendingapp.Investor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p2plendingapp.General.AgreeGeneralForm;
import com.example.p2plendingapp.General.ProfileSelection;
import com.example.p2plendingapp.Investor.InvestmentDetails.CheckInvestmentDetails;
import com.example.p2plendingapp.R;
import com.example.p2plendingapp.Support.EmailSupport;

public class MainDashboardInvestor extends AppCompatActivity {

    Button doAnInvestmentMDIBt, checkInvestmentDetailsMDIBt, talkWSupportMDIBt, getAccessPersonalInfoMDIBt;
    Intent sIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard_investor);

        doAnInvestmentMDIBt = findViewById(R.id.doAnInvestmentMDIBt);
        checkInvestmentDetailsMDIBt = findViewById(R.id.checkInvestmentDetailsMDIBt);
        talkWSupportMDIBt = findViewById(R.id.talkWSupportMDIBt);
        getAccessPersonalInfoMDIBt = findViewById(R.id.getAccessPersonalInfoMDIBt);

        //Set up listeners
        doAnInvestmentMDIBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sIntent = new Intent(getApplicationContext(), AgreeGeneralForm.class);
                sIntent.putExtra("redirect to", "investor");
                startActivity(sIntent);
            }
        });

        checkInvestmentDetailsMDIBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sIntent = new Intent(getApplicationContext(), CheckInvestmentDetails.class);
                startActivity(sIntent);
            }
        });

        talkWSupportMDIBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sIntent = new Intent(getApplicationContext(), EmailSupport.class);
                sIntent.putExtra("to main dashboard", "investorDashboard");
                startActivity(sIntent);
            }
        });
    }
}