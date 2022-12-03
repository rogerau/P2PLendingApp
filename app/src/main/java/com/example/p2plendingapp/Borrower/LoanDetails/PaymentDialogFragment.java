package com.example.p2plendingapp.Borrower.LoanDetails;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.p2plendingapp.R;

public class PaymentDialogFragment extends DialogFragment {


    public PaymentDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_payment, null))
                // Add action buttons
                .setPositiveButton(R.string.go_bank_app, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // redirect to the bank app...
                        //Set up the bank website
                        String website = "https://www.cibconline.cibc.com/ebm-resources/online-banking/client/index.html#/auth/signon";
                        //Parse the website into Uri object
                        Uri url = Uri.parse(website);
                        //Send the URI object into the intent to open the website by default browser
                        Intent intent = new Intent(Intent.ACTION_VIEW, url);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // user cancel the redirection
                    }
                });
        return builder.create();
    }
}