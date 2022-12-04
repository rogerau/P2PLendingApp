package com.example.p2plendingapp.Borrower;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RequiresApi(api = Build.VERSION_CODES.O)
public class DisplaySummary extends AppCompatActivity implements View.OnClickListener {

    Button acceptDisSumButton, rejectDisSumButton;
    Intent sIntent, lIntent;
    p2pLendingDB db;
    Loan aLoan;
    TextView riskLvlDisSumResult, borrowPDisSumResult, borrowADisSumResult, dateAgreeDisSumResult, interestRADisSumResult,
            oFeeDisSumResult, oFeeADisSumResult, mInstallmentDisSumResult;
    CheckBox checkLoanConditions;
    double bAmount, tAmountPaid, tAmountLeft;
    Boolean aTerms;
    String pOfBorrowing;
    int bPeriod, cId, temp1, temp2, temp3, temp4;
    String riskLvl;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd");
    LocalDate dateAgreement = LocalDate.now();
    LocalDate dateBefore, dateAfter;
    Period months_passed;
    NumberFormat percentage = NumberFormat.getPercentInstance();
    NumberFormat currency = NumberFormat.getCurrencyInstance();

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

        //Get data from the borrower application form
        getDataFromBaPPForm();
        //Send data into the loan object and store the data into the loan table
        storeLoanDataIntoLoanTable();
        //Once sent and stored, display data in the form
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
        //Set all values to the correct views
        riskLvlDisSumResult.setText(riskLvl);
        borrowPDisSumResult.setText(String.valueOf(bPeriod) + " months");
        borrowADisSumResult.setText(currency.format(bAmount));
        dateAgreeDisSumResult.setText(formatter.format(dateAgreement));
        interestRADisSumResult.setText(percentage.format(aLoan.getiRate()));
        oFeeDisSumResult.setText(percentage.format(aLoan.getoFee()));
        oFeeADisSumResult.setText(currency.format(aLoan.getoFeeAmount()));
        mInstallmentDisSumResult.setText(currency.format(aLoan.getmPAmount()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openCheckLoanDetails() {
        sIntent = new Intent(this, CheckLoanDetails.class);
        //Send data to the check loan details activity
        //Send the borrow amount
        sIntent.putExtra("borrow_amount", bAmount);

//      Calculate months passed since date of agreement
        dateBefore = dateAgreement;
        if (bPeriod <= 12) {
            dateAfter = LocalDate.of(2023, bPeriod, 03);
        } else if (bPeriod <= 24) {
            temp1 = bPeriod - 12;
            dateAfter = LocalDate.of(2024, temp1, 03);
        } else if (bPeriod <= 36) {
            temp2 = bPeriod - 24;
            dateAfter = LocalDate.of(2025, temp2, 03);
        } else if (bPeriod <= 48) {
            temp3 = bPeriod - 36;
            dateAfter = LocalDate.of(2026, temp3, 03);
        } else if (bPeriod <= 60) {
            temp4 = bPeriod - 48;
            dateAfter = LocalDate.of(2027, temp4, 03);
        } else {
            dateAfter = LocalDate.of(2027, temp4, 03);
        }

        months_passed = Period.between(dateBefore, dateAfter);
        int num_months_passed = months_passed.getMonths();

//      Calculate the total amount paid
        tAmountPaid = num_months_passed * aLoan.getmPAmount();

        //Calculate the total amount left
        tAmountLeft = bAmount - tAmountPaid;
        //Send the total amount paid
        sIntent.putExtra("payment_amount", tAmountPaid);
        //Send the total amount left
        sIntent.putExtra("amount_left", tAmountLeft);

        Date dateBefore2 = Date.from(dateBefore.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateAfter2 = Date.from(dateAfter.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //Send the payment schedule as string array plus status as string array too
        List paymentScheduleList = getDatesBetweenUsingJava7(dateBefore2, dateAfter2);
        ArrayList<String> statusArray = new ArrayList<>();
        ArrayList<String> paymentScheduleList2 = new ArrayList<>();
        for (int i = 0; i < paymentScheduleList.size(); i++) {
            paymentScheduleList2.add(formatter2.format(paymentScheduleList.get(i)));
            statusArray.add("unpaid");
        }
        sIntent.putStringArrayListExtra("payment_schedule", paymentScheduleList2);
        sIntent.putStringArrayListExtra("payment_status", statusArray);
        startActivity(sIntent);
    }

    public static List getDatesBetweenUsingJava7(Date startDate, Date endDate) {
        List datesInRange = new ArrayList<>();
        Calendar calendar = getCalendarWithoutTime(startDate);
        Calendar endCalendar = getCalendarWithoutTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }

        return datesInRange;
    }

    private static Calendar getCalendarWithoutTime(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
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
        aLoan.setaTerms(aTerms);
        aLoan.setlAmount(bAmount);
        aLoan.setpPeriod(bPeriod);
        aLoan.setmPAmount(bAmount);
        aLoan.setsDOAgreement(formatter.format(dateAgreement));
        aLoan.setcId(cId);
        //Insert the data into the Loan table
        long numInserted = db.insertIntoLoanTb(aLoan);
        Toast.makeText(this, numInserted + " row(s) were inserted!", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.acceptDisSumButton) {
            if (checkLoanConditions.isChecked()) {
                //Display check loan details
                openCheckLoanDetails();
            } else {
                Toast.makeText(this, "You must check first!", Toast.LENGTH_SHORT).show();
            }
        } else {
            returnToMainDashBoard();
        }
    }


}