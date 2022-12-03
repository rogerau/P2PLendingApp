package com.example.p2plendingapp.Borrower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.p2plendingapp.Database.p2pLendingDB;
import com.example.p2plendingapp.Model.Borrower;

import com.example.p2plendingapp.R;

public class BorrowersApplicationForm extends AppCompatActivity implements View.OnClickListener {
    Intent sIntent, lIntent;
    Button submitAppFormButton, declineAppFormButton;
    p2pLendingDB db;
    Borrower aBorrower;
    RadioGroup resStatusAppForm, maritalStatusAppForm, pStatusAppForm, pOfBorrowingAppForm;
    EditText cBankAccAppFormEt, borrowAAppFormEt, borrowPAPPFormEt, netIncomeAppFormEt, netIncomeSpouseAppFormEt, expensesAppFormEt;
    int randomCId, resStatusId, mStatusId, pStatusId, pOfBorrowingId;
    double borrowA;
    String cBankAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowers_application_form);

        submitAppFormButton = findViewById(R.id.submitAppFormButton);
        declineAppFormButton = findViewById(R.id.declineAppFormButton);
        netIncomeAppFormEt = findViewById(R.id.netIncomeAppFormEt);
        netIncomeSpouseAppFormEt = findViewById(R.id.netIncomeSpouseAppFormEt);
        expensesAppFormEt = findViewById(R.id.expensesAppFormEt);
        resStatusAppForm = findViewById(R.id.resStatusAppForm);
        maritalStatusAppForm = findViewById(R.id.maritalStatusAppForm);
        pStatusAppForm = findViewById(R.id.pStatusAppForm);

        pOfBorrowingAppForm = findViewById(R.id.pOfBorrowingAppForm);
        borrowAAppFormEt = findViewById(R.id.borrowAAppFormEt);
        borrowPAPPFormEt = findViewById(R.id.borrowPAPPFormEt);

        cBankAccAppFormEt = findViewById(R.id.cBankAccAppFormEt);

        //Set up listeners
        submitAppFormButton.setOnClickListener(this);
        declineAppFormButton.setOnClickListener(this);

        db = new p2pLendingDB(this);
        aBorrower = new Borrower();

    }

    public void displayLoanSummary() {
        lIntent = getIntent();
        //Receive the agreedTerms from agree general conditions activity
        Boolean agreedTerms = lIntent.getBooleanExtra("Agreed Terms", true);

        //Receive the customerId from agree general conditions activity

        sIntent = new Intent(this, DisplaySummary.class);

        //Continue passing the agreedTerms into the display summary activity
        sIntent.putExtra("agreed_terms", agreedTerms);

        //Continue passing the customerId into the display summary activity
        //Generate a random integer 4 digits as a lId from 1000 to 9999 (temporal)
        int min = 1000;
        int max = 9999;
        randomCId = (int) Math.floor(Math.random() * (max - min + 1) + min);
        sIntent.putExtra("customer_id", randomCId);

        //Validate inputs
        cBankAcc = cBankAccAppFormEt.getText().toString();
        if (cBankAcc.isEmpty()) {
            Toast.makeText(this, "Please, insert a canadian bank account", Toast.LENGTH_SHORT).show();
        } else {
            borrowA = Double.parseDouble(borrowAAppFormEt.getText().toString());
            if (borrowA > 25000 || borrowA < 1000) {
                Toast.makeText(this, "Please, insert a borrow amount greater that or equal to $1000 and lower than or equal to $25000", Toast.LENGTH_SHORT).show();
            } else {
                //Get the id of every radio button inside purpose of borrowing radio group
                pOfBorrowingId = pOfBorrowingAppForm.getCheckedRadioButtonId();
                if (pOfBorrowingId == -1) {
                    Toast.makeText(this, "Please, you must check an option in purpose of borrowing", Toast.LENGTH_SHORT).show();
                } else {
                    if (borrowPAPPFormEt.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Please, you must enter a borrow period", Toast.LENGTH_SHORT).show();
                    } else {
                        //Get the id of every radio button inside residency status radio group
                        resStatusId = resStatusAppForm.getCheckedRadioButtonId();
                        if (resStatusId == -1) {
                            Toast.makeText(this, "Please, you must check an option in residency status", Toast.LENGTH_SHORT).show();
                        } else {
                            //Get the id of every radio button inside marital status radio group
                            mStatusId = maritalStatusAppForm.getCheckedRadioButtonId();
                            if (mStatusId == -1) {
                                Toast.makeText(this, "Please, you must check an option in marital status", Toast.LENGTH_SHORT).show();
                            } else {
                                if (netIncomeAppFormEt.getText().toString().isEmpty()) {
                                    Toast.makeText(this, "Please, you must insert a net income", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (netIncomeSpouseAppFormEt.getText().toString().isEmpty()) {
                                        Toast.makeText(this, "Please, you must insert a spouse net income", Toast.LENGTH_SHORT).show();
                                    } else {
                                        //Get the id of every radio button inside property status radio group
                                        pStatusId = pStatusAppForm.getCheckedRadioButtonId();
                                        if (pStatusId == -1) {
                                            Toast.makeText(this, "Please, you must check an option in property status", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (expensesAppFormEt.getText().toString().isEmpty()) {
                                                Toast.makeText(this, "Please, you must insert expenses amount", Toast.LENGTH_SHORT).show();
                                            } else {
                                                //Pass the following data to the display summary activity
                                                sIntent.putExtra("borrow_amount", borrowA);
                                                if (pOfBorrowingId == R.id.goodOptionAppForm) {
                                                    sIntent.putExtra("purpose_borrowing", "buy a good");
                                                } else if (pOfBorrowingId == R.id.debtOptionAppForm) {
                                                    sIntent.putExtra("purpose_borrowing", "pay debt");
                                                } else if (pOfBorrowingId == R.id.housingOptionAppForm) {
                                                    sIntent.putExtra("purpose_borrowing", "buy house/apartment");
                                                } else if (pOfBorrowingId == R.id.carOptionAppForm) {
                                                    sIntent.putExtra("purpose_borrowing", "buy a car");
                                                } else {
                                                    sIntent.putExtra("purpose_borrowing", "other");
                                                }
                                                sIntent.putExtra("borrow_period", Integer.parseInt(borrowPAPPFormEt.getText().toString()));
                                                sIntent.putExtra("risk_lvl", aBorrower.setRiskLvl());
                                                startActivity(sIntent);
                                                //Store borrower data into the borrower table
                                                storeBorrowerDataIntoBorrowerTable();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public void returnToMainDashBoard() {
        sIntent = new Intent(this, MainDashboard.class);
        startActivity(sIntent);
    }

    public void storeBorrowerDataIntoBorrowerTable() {
        //Here you must store the cId into the borrower table.
        aBorrower.setbId(randomCId);
        aBorrower.setHmExp(Double.parseDouble(expensesAppFormEt.getText().toString()));
        aBorrower.setmNetIncome(Double.parseDouble(netIncomeAppFormEt.getText().toString()));
        aBorrower.setmSNetIncome(Double.parseDouble(netIncomeSpouseAppFormEt.getText().toString()));
        aBorrower.setcBAccount(cBankAccAppFormEt.getText().toString());
        //Get the id of every radio button inside residency status radio group
        //Assign the respective value to the Borrower object
        if (resStatusId == R.id.tOptionAppForm) {
            aBorrower.setrStatus("temporary");
        } else {
            aBorrower.setrStatus("permanent");
        }
        //Get the id of every radio button inside marital status radio group
        //Assign the respective value to the Borrower object
        if (mStatusId == R.id.sOptionAppForm) {
            aBorrower.setmStatus("single");
        } else {
            aBorrower.setmStatus("married");
        }
        //Get the id of every radio button inside property status radio group
        //Assign the respective value to the Borrower object
        if (pStatusId == R.id.rOptionAppForm) {
            aBorrower.setpStatus("rent");
        } else {
            aBorrower.setpStatus("own");
        }
        //Insert the data into the Borrowers table
        long numInserted = db.insertIntoBorrowersTb(aBorrower);
        Toast.makeText(this, numInserted + " row(s) were inserted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.declineAppFormButton) {
            returnToMainDashBoard();
        } else {
            //Display loan summary and pass the respective data
            displayLoanSummary();
        }
    }

}