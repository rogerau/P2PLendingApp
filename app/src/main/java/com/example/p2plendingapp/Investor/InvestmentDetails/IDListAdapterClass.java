package com.example.p2plendingapp.Investor.InvestmentDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.p2plendingapp.Model.InvestmentDetails;
import com.example.p2plendingapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;

public class IDListAdapterClass extends ArrayAdapter<InvestmentDetails> {

    TextView investmentId, iAmount, aEarnings, profitRatio;
    NumberFormat percentage = NumberFormat.getPercentInstance();
    NumberFormat currency = NumberFormat.getCurrencyInstance();

    public IDListAdapterClass(Context context, ArrayList<InvestmentDetails> investmentDetails) {
        super(context, 0, investmentDetails);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InvestmentDetails investmentDetails = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.idetails_items, parent, false);
        }
        // Lookup view for data population
        investmentId = (TextView) convertView.findViewById(R.id.investmentId);
        iAmount = (TextView) convertView.findViewById(R.id.iAmount);
        aEarnings = (TextView) convertView.findViewById(R.id.aEarnings);
        profitRatio = (TextView) convertView.findViewById(R.id.profitRatio);
        // Populate the data into the template view using the data object
        investmentId.setText(String.valueOf(investmentDetails.getInvestmentId()));
        iAmount.setText(currency.format(investmentDetails.getInvestmentA()));
        aEarnings.setText(currency.format(investmentDetails.calEarningsAcc()));
        profitRatio.setText(percentage.format(investmentDetails.calProfitRatio()));
        // Return the completed view to render on screen
        return convertView;
    }


}
