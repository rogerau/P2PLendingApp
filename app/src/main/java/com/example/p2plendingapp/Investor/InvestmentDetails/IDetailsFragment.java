package com.example.p2plendingapp.Investor.InvestmentDetails;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.p2plendingapp.Model.InvestmentDetails;
import com.example.p2plendingapp.R;

import java.util.ArrayList;

public class IDetailsFragment extends Fragment {
    IDListAdapterClass adapter;
    ListView listView;
    Context thisContext;

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

        listView = (ListView)rootView.findViewById(R.id.iDetailsList);

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
        ArrayList<InvestmentDetails> iDetails = new ArrayList<InvestmentDetails>();
        iDetails.add(new InvestmentDetails(1234, 5000, 60 ));
        iDetails.add(new InvestmentDetails(1234, 5000, 60 ));
        iDetails.add(new InvestmentDetails(1234, 5000, 60 ));
        iDetails.add(new InvestmentDetails(1234, 5000, 60 ));
        iDetails.add(new InvestmentDetails(1234, 5000, 60 ));
        iDetails.add(new InvestmentDetails(1234, 5000, 60 ));
        iDetails.add(new InvestmentDetails(1234, 5000, 60 ));
        iDetails.add(new InvestmentDetails(1234, 5000, 60 ));
        iDetails.add(new InvestmentDetails(1234, 5000, 60 ));
        iDetails.add(new InvestmentDetails(1234, 5000, 60 ));
        return iDetails;

    }
}