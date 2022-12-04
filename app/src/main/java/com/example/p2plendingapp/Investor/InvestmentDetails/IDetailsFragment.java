package com.example.p2plendingapp.Investor.InvestmentDetails;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.p2plendingapp.Database.p2pLendingDB;
import com.example.p2plendingapp.Model.InvestmentDetails;
import com.example.p2plendingapp.Model.Opportunities;
import com.example.p2plendingapp.R;

import java.util.ArrayList;

public class IDetailsFragment extends Fragment {
    IDListAdapterClass adapter;
    ListView listView;
    Context thisContext;
    p2pLendingDB db;

    public IDetailsFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get the context of the activity that holds the fragment
        thisContext = container.getContext();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_i_details, container, false);

        listView = (ListView) rootView.findViewById(R.id.iDetailsList);

        db = new p2pLendingDB(thisContext);
        //Set the adapter
        setAdapter();
        return rootView;
    }

    public void setAdapter() {
        //Setting the adapter
        adapter = new IDListAdapterClass(thisContext, populateAndGetList());
        listView.setAdapter(adapter);
    }


    public ArrayList<InvestmentDetails> populateAndGetList() {
        //Get data from the investment table (mEarning, investment Id)
//        ArrayList<String> aList = db.extractFromInvestmentTbForCID();
//        int iId = Integer.parseInt(aList.subList(0, 0).toString());
//        double mEarnings = Double.parseDouble(aList.subList(1, 1).toString());
        //Get data from the loan table (borrow amount, borrow period)
//        ArrayList<String> aList2 = db.extractFromLoanTbForMPA();
//        double bAmount = Double.parseDouble(aList2.subList(0, 0).toString());
//        int pPeriod = Integer.parseInt(aList2.subList(1, 1).toString());

        //Temporal
        int[] iId = {1, 2, 3, 4, 5};
        double[] bAmount = {5000, 400, 6000, 1000, 6000};
        double[] mEarnings = {100, 200, 500, 50, 600};
        int[] pPeriod = {12, 24, 36, 12, 24};
        ArrayList<InvestmentDetails> iDetails = new ArrayList<InvestmentDetails>();
        for (int i = 0; i < bAmount.length; i++) {
            iDetails.add(new InvestmentDetails(iId[i], bAmount[i], mEarnings[i], pPeriod[i]));
        }

        return iDetails;

    }
}