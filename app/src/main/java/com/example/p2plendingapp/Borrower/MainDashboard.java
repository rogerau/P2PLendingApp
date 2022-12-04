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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainDashboard extends AppCompatActivity implements View.OnClickListener {

    Button doABorrowMDBt, checkLoanDetailsMDBt, talkWSupportMDBt, getAccessPersonalInfoMDBt;
    Intent sIntent, lIntent;
    double bAmount, pAmount, aLeft;
    ArrayList<String> pStatus, datesLeft;

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

    @Override
    protected void onResume() {
        super.onResume();
        receiveDataFromOpenCheckLoanDetails();
    }

    public void openTalkWithSupport() {
        sIntent = new Intent(this, EmailSupport.class);
        sIntent.putExtra("to main dashboard", "borrowerDashboard");
        startActivity(sIntent);
    }

    public void openCheckLoanDetails() {
        sIntent = new Intent(this, CheckLoanDetails.class);
        //Send the data that came back from CheckLoanDetails
        sIntent.putExtra("borrow_amount", bAmount);
        sIntent.putExtra("payment_amount", pAmount);
        sIntent.putExtra("amount_left", aLeft);
        sIntent.putStringArrayListExtra("payment_schedule", datesLeft);
        sIntent.putStringArrayListExtra("payment_status", pStatus);
        startActivity(sIntent);
    }

    public void openAgreeRequirementsForm() {
        sIntent = new Intent(this, AgreeGeneralForm.class);
        sIntent.putExtra("redirect to", "borrower");
        startActivity(sIntent);
    }

    public void receiveDataFromOpenCheckLoanDetails() {
        lIntent = getIntent();
        bAmount = lIntent.getDoubleExtra("borrow_amount", 0);
        pAmount = lIntent.getDoubleExtra("payment_amount", 0);
        aLeft = lIntent.getDoubleExtra("amount_left", 0);
        datesLeft = lIntent.getStringArrayListExtra("payment_schedule");
        pStatus = lIntent.getStringArrayListExtra("payment_status");

    }
}