package com.example.p2plendingapp.Borrower;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2plendingapp.Borrower.LoanDetails.CheckLoanDetails;
import com.example.p2plendingapp.Database.p2pLendingDB;
import com.example.p2plendingapp.Model.Loan;
import com.example.p2plendingapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DisplaySummary extends AppCompatActivity implements View.OnClickListener {

    Button acceptDisSumButton, rejectDisSumButton;
    Intent sIntent, lIntent;
    p2pLendingDB db;
    Loan aLoan;
    TextView riskLvlDisSumResult, borrowPDisSumResult, borrowADisSumResult, dateAgreeDisSumResult, interestRADisSumResult,
            oFeeDisSumResult, oFeeADisSumResult, mInstallmentDisSumResult;
    CheckBox checkLoanConditions;
    double bAmount;
    Boolean aTerms;
    String pOfBorrowing;
    int bPeriod, cId;
    String riskLvl;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_summary);

        acceptDisSumButton = findViewById(R.id.acceptDisSumButton);
        rejectDisSumButton = findViewById(R.id.rejectDisSumButton);
        riskLvlDisSumResult = findViewById(R.id.riskLvlDisSumResult);
        borrowPDisSumResult = findViewById(R.id.borrowPDisSumResult);
        borrowADisSumResult = findViewById(R.id.borrowADisSumResult);
        dateAgreeDisSumResult = findViewById(R.id.dateAgreeDisSumResult);
        interestRADisSumResult = findViewById(R.id.interestRADisSumResult);
        oFeeDisSumResult = findViewById(R.id.oFeeDisSumResult);
        oFeeADisSumResult = findViewById(R.id.oFeeADisSumResult);
        oFeeDisSumResult = findViewById(R.id.oFeeDisSumResult);
        mInstallmentDisSumResult = findViewById(R.id.mInstallmentDisSumResult);
        checkLoanConditions = findViewById(R.id.checkLoanConditions);

        //Set up the listeners
        acceptDisSumButton.setOnClickListener(this);
        rejectDisSumButton.setOnClickListener(this);

        db = new p2pLendingDB(this);
        aLoan = new Loan();

        //Display data sent from the borrower application form
        displayDataFromBAppForm();

    }

    public void getDataFromBaPPForm() {
        //Get data sent by the borrowers app form
        lIntent = getIntent();
        pOfBorrowing = lIntent.getStringExtra("purpose_borrowing");
        aTerms = lIntent.getBooleanExtra("agreed_terms", false);
        bAmount = lIntent.getDoubleExtra("borrow_amount", 0);
        bPeriod = lIntent.getIntExtra("borrow_period", 0);
        riskLvl = lIntent.getStringExtra("risk_lvl");
        //Get customer id sent by the borrowers app form
        cId = lIntent.getIntExtra("customer_id", 0);
    }

    public void displayDataFromBAppForm() {
        //Get data from borrowers application form
        getDataFromBaPPForm();
        //Set all values to the correct views
        riskLvlDisSumResult.setText(riskLvl);
        borrowPDisSumResult.setText(String.valueOf(bPeriod));
        borrowADisSumResult.setText(String.valueOf(bAmount));
        dateAgreeDisSumResult.setText(formatter.format(date));
        interestRADisSumResult.setText(String.valueOf(aLoan.getiRate()));
        oFeeDisSumResult.setText(String.valueOf(aLoan.getoFee()));
        oFeeADisSumResult.setText(String.valueOf(aLoan.getoFeeAmount()));
        mInstallmentDisSumResult.setText(String.valueOf(aLoan.getmPAmount()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openCheckLoanDetails() {
        sIntent = new Intent(this, CheckLoanDetails.class);
        //Send data to the check loan details activity
        //Send the borrow amount
        sIntent.putExtra("borrow_amount", bAmount);
        //Send the total amount paid
        sIntent.putExtra("payment_amount", 0);
        //Send the total amount left
        sIntent.putExtra("amount_left", 0);
        //Send the payment schedule as string array plus status as string array too
        ArrayList<String> paymentScheduleList = new ArrayList<>();
        ArrayList<String> statusArray = new ArrayList<>();
        sIntent.putStringArrayListExtra("payment_schedule", paymentScheduleList);
        sIntent.putStringArrayListExtra("payment_status", statusArray);
        startActivity(sIntent);
    }

    public void returnToMainDashBoard() {
        sIntent = new Intent(this, MainDashboard.class);
        startActivity(sIntent);
    }

    public void storeLoanDataIntoLoanTable() {
        //Store loan data into the Loan object
        //Generate a random integer 5 digits as a lId from 10000 to 99999
        int min = 10000;
        int max = 99999;
        int randomLId = (int) Math.floor(Math.random() * (max - min + 1) + min);
        //Store the lId as a random number (temporary)
        aLoan.setlId(randomLId);
        aLoan.setpOBorrowing(pOfBorrowing);
        aLoan.setoFee(bAmount);
        aLoan.setiRate(bAmount, bPeriod, riskLvl);
        aLoan.setuPFee();
        aLoan.setlPFee();
        aLoan.setmPAmount(bAmount);
        aLoan.setaTerms(aTerms);
        aLoan.setlAmount(bAmount);
        aLoan.setpPeriod(bPeriod);
        aLoan.setsDOAgreement(formatter.format(date));
        aLoan.setcId(cId);
        //Insert the data into the Loan table
        long numInserted = db.insertIntoLoanTb(aLoan);
        Toast.makeText(this, numInserted + " row(s) were inserted!", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.acceptDisSumButton) {
            if (checkLoanConditions.isChecked()) {
                //Display check loan details
                openCheckLoanDetails();
                //Store loan data into the loan table
                storeLoanDataIntoLoanTable();
            } else {
                Toast.makeText(this, "You must check first!", Toast.LENGTH_SHORT).show();
            }
        } else {
            returnToMainDashBoard();
        }
    }


}