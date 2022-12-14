package com.example.p2plendingapp.Borrower.LoanDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.p2plendingapp.Borrower.MainDashboard;
import com.example.p2plendingapp.R;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CheckLoanDetails extends AppCompatActivity implements View.OnClickListener {

    Intent sIntent, lIntent;
    Button goBackLoanDBt;
    FragmentManager manager;
    FragmentTransaction transaction;
    double bAmount, pAmount, aLeft;
    ArrayList<String> datesLeft = new ArrayList<>(), pStatus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_loan_details);

        goBackLoanDBt = findViewById(R.id.goBackLoanDBt);
        manager = getSupportFragmentManager();

        //Set up listeners
        goBackLoanDBt.setOnClickListener(this);

        //Get data from display summary activity
        getDataFromDisplaySummaryActivity();

    }


    public void getDataFromDisplaySummaryActivity() {
        lIntent = getIntent();
        bAmount = lIntent.getDoubleExtra("borrow_amount", 0);
        pAmount = lIntent.getDoubleExtra("payment_amount", 0);
        aLeft = lIntent.getDoubleExtra("amount_left", 0);
        datesLeft = lIntent.getStringArrayListExtra("payment_schedule");
        pStatus = lIntent.getStringArrayListExtra("payment_status");
    }

    public void addDetailFragment(View view) {
        getDataFromDisplaySummaryActivity();
        DetailsFragment detailsFragment = new DetailsFragment(bAmount, pAmount, aLeft, datesLeft, pStatus);
        transaction = manager.beginTransaction();
        transaction.add(R.id.detailsContainerLoanD, detailsFragment, "details_fragment");
        transaction.commit();
    }

    public void removeDetailFragment(View view) {
        DetailsFragment detailsFragment = (DetailsFragment) manager.findFragmentByTag("details_fragment");
        transaction = manager.beginTransaction();
        transaction.remove(detailsFragment);
        transaction.commit();
    }

    public void showPaymentDialog(View view) {
        PaymentDialogFragment dialogFragment = new PaymentDialogFragment();
        manager = getSupportFragmentManager();
        dialogFragment.show(manager, "payment");
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goBackLoanDBt) {
            returnToMainDashBoard();
        }
    }

    public void returnToMainDashBoard() {
        sIntent = new Intent(this, MainDashboard.class);
        //Send data to the main dashboard
        sIntent.putExtra("borrow_amount", bAmount);
        sIntent.putExtra("payment_amount", pAmount);
        sIntent.putExtra("amount_left", aLeft);
        sIntent.putStringArrayListExtra("payment_schedule", datesLeft);
        sIntent.putStringArrayListExtra("payment_status", pStatus);
        startActivity(sIntent);
    }
}