package com.example.p2plendingapp.Borrower.LoanDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.p2plendingapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;

public class DetailsFragment extends Fragment {

    TextView originalLALoanDResult, tAmountPaidLoanDResult, tAmountLeftLoanDResult, datesLeftLoanDResult, pStatusLoanDResult;
    Context thisContext;
    double bAmount, pAmount, aLeft;
    ArrayList<String> datesLeft = new ArrayList<>(), pStatus = new ArrayList<>();
    NumberFormat currency = NumberFormat.getCurrencyInstance();

    public DetailsFragment(double bAmount, double pAmount, double aLeft, ArrayList<String> datesLeft, ArrayList<String> pStatus) {
        this.bAmount = bAmount;
        this.pAmount = pAmount;
        this.aLeft = aLeft;
        this.datesLeft = datesLeft;
        this.pStatus = pStatus;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Get the context of the activity that holds the fragment
        thisContext = container.getContext();

        // Inflate the layout for this fragment
        View view = getLayoutInflater().inflate(R.layout.fragment_details, container, false);

        tAmountPaidLoanDResult = (TextView) view.findViewById(R.id.tAmountPaidLoanDResult);
        originalLALoanDResult = (TextView) view.findViewById(R.id.originalLALoanDResult);
        tAmountLeftLoanDResult = (TextView) view.findViewById(R.id.tAmountLeftLoanDResult);
        datesLeftLoanDResult = (TextView) view.findViewById(R.id.datesLeftLoanDResult);
        pStatusLoanDResult = (TextView) view.findViewById(R.id.pStatusLoanDResult);

        originalLALoanDResult.setText(currency.format(bAmount));
        tAmountPaidLoanDResult.setText(currency.format(pAmount));
        tAmountLeftLoanDResult.setText(currency.format(aLeft));
        for (int i = 0; i < datesLeft.size(); i++) {
            datesLeftLoanDResult.setText(datesLeft.get(i) + "\n");
        }
        for (int i = 0; i < pStatus.size(); i++) {
            pStatusLoanDResult.setText(pStatus.get(i) + "\n");
        }

        return view;
    }

}