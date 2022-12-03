package com.example.p2plendingapp.Borrower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p2plendingapp.Borrower.LoanDetails.CheckLoanDetails;
import com.example.p2plendingapp.General.AgreeGeneralForm;
import com.example.p2plendingapp.R;
import com.example.p2plendingapp.Support.EmailSupport;

public class MainDashboard extends AppCompatActivity implements View.OnClickListener {

    Button doABorrowMDBt, checkLoanDetailsMDBt, talkWSupportMDBt, getAccessPersonalInfoMDBt;
    Intent sIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        doABorrowMDBt = findViewById(R.id.doABorrowMDBt);
        checkLoanDetailsMDBt = findViewById(R.id.checkLoanDetailsMDBt);
        talkWSupportMDBt = findViewById(R.id.talkWSupportMDBt);
        getAccessPersonalInfoMDBt = findViewById(R.id.getAccessPersonalInfoMDBt);

        //Set up listeners
        doABorrowMDBt.setOnClickListener(this);
        checkLoanDetailsMDBt.setOnClickListener(this);
        talkWSupportMDBt.setOnClickListener(this);
        getAccessPersonalInfoMDBt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.doABorrowMDBt) {
            openAgreeRequirementsForm();
        } else if (v.getId() == R.id.checkLoanDetailsMDBt) {
            openCheckLoanDetails();
        } else {
            openTalkWithSupport();
        }
    }


    public void openTalkWithSupport() {
        sIntent = new Intent(this, EmailSupport.class);
        sIntent.putExtra("to main dashboard", "borrowerDashboard");
        startActivity(sIntent);
    }

    public void openCheckLoanDetails() {
        sIntent = new Intent(this, CheckLoanDetails.class);
        startActivity(sIntent);
    }

    public void openAgreeRequirementsForm() {
        sIntent = new Intent(this, AgreeGeneralForm.class);
        sIntent.putExtra("redirect to", "borrower");
        startActivity(sIntent);
    }
}