package com.example.p2plendingapp.Investor.MarketPlace_Java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.p2plendingapp.Model.Opportunities;
import com.example.p2plendingapp.R;

import java.util.ArrayList;

public class ListAdapterClass extends ArrayAdapter<Opportunities> {

    TextView id, borrowerId, borrowerAmount, borrowPeriod, riskLvl, eEarnings;
    CheckBox checkBox;

    public ListAdapterClass(Context context, ArrayList<Opportunities> opportunities) {
        super(context, 0, opportunities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Opportunities opportunities = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }
        // Lookup view for data population
        id = (TextView) convertView.findViewById(R.id.id);
        borrowerId = (TextView) convertView.findViewById(R.id.borrowerId);
        borrowerAmount = (TextView) convertView.findViewById(R.id.borrowerAmount);
        borrowPeriod = (TextView) convertView.findViewById(R.id.borrowPeriod);
        riskLvl = (TextView) convertView.findViewById(R.id.riskLvl);
        eEarnings = (TextView) convertView.findViewById(R.id.eEarnings);
        checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        // Populate the data into the template view using the data object
        id.setText(String.valueOf(opportunities.getId()));
        borrowerId.setText(opportunities.getBorrowerId());
        borrowerAmount.setText(String.valueOf(opportunities.getBorrowAmount()));
        borrowPeriod.setText(String.valueOf(opportunities.getBorrowPeriod()));
        riskLvl.setText(opportunities.getRiskLvl());
        eEarnings.setText(String.valueOf(opportunities.geteEarnings()));
        checkBox.setChecked(opportunities.isChecked());
        // Return the completed view to render on screen
        return convertView;
    }

}
