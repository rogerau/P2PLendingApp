package com.example.p2plendingapp.General;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.p2plendingapp.Borrower.BorrowersApplicationForm;
import com.example.p2plendingapp.Borrower.MainDashboard;
import com.example.p2plendingapp.Database.p2pLendingDB;
import com.example.p2plendingapp.Investor.InvestorsApplicationForm;
import com.example.p2plendingapp.Investor.MainDashboardInvestor;
import com.example.p2plendingapp.R;

public class AgreeGeneralForm extends AppCompatActivity {

    Intent sIntent, lIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agree_general_form);

    }

    public void agreeWithRequirements(View view) {
        lIntent = getIntent();
        String who = lIntent.getStringExtra("redirect to");
        if (who.equals("investor")) {
            sIntent = new Intent(this, InvestorsApplicationForm.class);
            sIntent.putExtra("Agreed Terms", true);
            startActivity(sIntent);
        } else {
            sIntent = new Intent(this, BorrowersApplicationForm.class);
            sIntent.putExtra("Agreed Terms", true);
            startActivity(sIntent);
        }
    }

    public void goBackToMainDashBoard(View view) {
        lIntent = getIntent();
        String who = lIntent.getStringExtra("redirect to");
        if (who.equals("investor")) {

            sIntent = new Intent(this, MainDashboardInvestor.class);
            sIntent.putExtra("Rejected Terms", false);
            startActivity(sIntent);
        } else {
            sIntent = new Intent(this, MainDashboard.class);
            sIntent.putExtra("Rejected Terms", false);
            startActivity(sIntent);
        }

    }


}