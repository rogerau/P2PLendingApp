package com.example.p2plendingapp.Support;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.p2plendingapp.Borrower.MainDashboard;
import com.example.p2plendingapp.Investor.MainDashboardInvestor;
import com.example.p2plendingapp.R;

public class EmailSupport extends AppCompatActivity {

    EditText etTo, etSubject, etMessage;
    Button btnSend, btngoBack;
    Intent sIntent, lIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_support);

        etTo = findViewById(R.id.et_to);
        etSubject = findViewById(R.id.et_subject);
        etMessage = findViewById(R.id.et_message);
        btnSend = findViewById(R.id.btn_send);
        btngoBack = findViewById(R.id.btn_goBack);

        btngoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lIntent = getIntent();
                String which = lIntent.getStringExtra("to main dashboard");
                if (which.equals("investorDashboard")) {
                    sIntent = new Intent(getApplicationContext(), MainDashboardInvestor.class);
                    startActivity(sIntent);
                } else {
                    sIntent = new Intent(getApplicationContext(), MainDashboard.class);
                    startActivity(sIntent);
                }

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + etTo.getText().toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, etMessage.getText().toString());
                startActivity(intent);
            }
        });


    }
}