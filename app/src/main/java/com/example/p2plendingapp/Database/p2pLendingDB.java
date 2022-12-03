package com.example.p2plendingapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.p2plendingapp.Model.Borrower;
import com.example.p2plendingapp.Model.Investor;
import com.example.p2plendingapp.Model.Loan;

public class p2pLendingDB extends SQLiteOpenHelper {

    //Set up references
    protected static final String DB_NAME = "P2pLending";
    protected static final int DB_VERSION = 1;

    //Borrower table details
    protected final String borrowerTB = "Borrower";
    protected final String bId = "BorrowerId";
    protected final String hmExp = "HouseholdExp";
    protected final String rStatus = "ResidencyStatus";
    protected final String mNetIncome = "NetIncome";
    protected final String mSNetIncome = "SpouseNetIncome";
    protected final String mStatus = "MaritalStatus";
    protected final String pStatus = "PropertyStatus";
    protected final String bcbAcc = "BorrowerCanadianBankAcc";

    //Investor table details
    protected final String investorTB = "Investor";
    protected final String inId = "InvestorId";
    protected final String sOFunds = "SourceFunds";
    protected final String pRLevel = "RiskLevelPref";
    protected final String icbAcc = "InvestorCanadianBankAcc";

    //Customer table details
    protected final String customerTB = "Customer";
    protected final String cId = "CustomerId";
    protected final String dOB = "D0B";
    protected final String fName = "FirstName";
    protected final String lName = "LastName";


    //Investment table details
    protected final String investmentTB = "Investment";
    protected final String iId = "InvestmentId";
    protected final String sFee = "ServiceFee";
    protected final String mEarnings = "Earnings";
    protected final String aDeal = "AcceptDeal";
    protected final String aTerms = "AgreeTerms";
    protected final String tLAmount = "LoanAmountTransferred";

    //Loan table details
    protected final String loanTB = "Loan";
    protected final String lId = "LoanId";
    protected final String pOBorrowing = "BorrowingPurpose";
    protected final String oFee = "OriginationFee";
    protected final String iRate = "InterestRate";
    protected final String uPFee = "UnpayFee";
    protected final String lPFee = "LateFee";
    protected final String mPAmount = "PaymentAmount";
    protected final String lAmount = "LoanAmount";
    protected final String sDOAgreement = "DateOfAgreement";
    protected final String pPeriod = "PaymentPeriod";

    //Transaction table details
    protected final String transactionTB = "Transaction";
    protected final String tId = "TransactionId";
    protected final String tDesc = "TransactionDesc";

    String[] tableNameArray;
    protected SQLiteDatabase myDb;
    protected long numRecordsInserted;
    protected int numRecordsDeleted;

    public p2pLendingDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create each table

        String customerTBCreate = "create table "
                + customerTB + " ("
                + cId + " integer primary key, "
                + dOB + " text not null, "
                + fName + " text not null, "
                + lName + " text not null"
                + ");";

        String borrowerTBCreate = "create table "
                + borrowerTB + " ("
                + bId + " integer primary key, "
                + hmExp + " double not null, "
                + rStatus + " text not null, "
                + mNetIncome + " double not null, "
                + mSNetIncome + " double not null, "
                + pStatus + " text not null, "
                + mStatus + " text not null, "
                + bcbAcc + " text not null unique, "
                + " FOREIGN KEY (" + bId + ") REFERENCES " + customerTB + "(" + cId + "));";

        String investorTBCreate = "create table "
                + investorTB + " ("
                + inId + " integer primary key, "
                + sOFunds + " text not null, "
                + pRLevel + " text not null, "
                + icbAcc + " text not null unique, "
                + " FOREIGN KEY (" + inId + ") REFERENCES " + customerTB + "(" + cId + "));";

        String investmentTBCreate = "create table "
                + investmentTB + " ("
                + iId + " integer primary key, "
                + sFee + " double not null, "
                + mEarnings + " double not null, "
                + aDeal + " boolean not null, "
                + aTerms + " boolean not null, "
                + tLAmount + " double not null, "
                + cId + " integer not null, "
                + " FOREIGN KEY (" + cId + ") REFERENCES " + customerTB + "(" + cId + "));";

        String loanTBCreate = "create table "
                + loanTB + " ("
                + lId + " integer primary key, "
                + pOBorrowing + " text not null, "
                + oFee + " double not null, "
                + iRate + " double not null, "
                + uPFee + " double not null, "
                + lPFee + " double not null, "
                + mPAmount + " double not null, "
                + aTerms + " boolean not null, "
                + lAmount + " double not null, "
                + pPeriod + " integer not null, "
                + sDOAgreement + " text not null, "
                + cId + " integer not null, "
                + " FOREIGN KEY (" + cId + ") REFERENCES " + customerTB + "(" + cId + "));";


            String transactionTBCreate = "create table "
                    + transactionTB + " ("
                    + tId + " integer primary key, "
                    + tDesc + " text not null unique"
                    + ");";

        tableNameArray = new String[]{customerTBCreate, borrowerTBCreate, investorTBCreate, investmentTBCreate, loanTBCreate, transactionTBCreate};
        try {
            for (int i = 0; i < tableNameArray.length; i++) {
                db.execSQL(tableNameArray[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("Database", "Table(s) successfully created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            for (int i = 0; i < tableNameArray.length; i++) {
                db.execSQL("drop table if exists " + tableNameArray[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        onCreate(db);
        Log.i("Database", "Table(s) successfully dropped!");
    }

    public long insertIntoBorrowersTb(Borrower aBorrower) {
        myDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bId, aBorrower.getbId());
        values.put(hmExp, aBorrower.getHmExp());
        values.put(rStatus, aBorrower.getrStatus());
        values.put(mNetIncome, aBorrower.getmNetIncome());
        values.put(mSNetIncome, aBorrower.getmSNetIncome());
        values.put(mStatus, aBorrower.getmStatus());
        values.put(pStatus, aBorrower.getpStatus());
        values.put(bcbAcc, aBorrower.getcBAccount());

        try {
            numRecordsInserted = myDb.insert(borrowerTB, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRecordsInserted;
    }

    public long insertIntoLoanTb(Loan aLoan) {
        myDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(pOBorrowing, aLoan.getpOBorrowing());
        values.put(oFee, aLoan.getoFee());
        values.put(iRate, aLoan.getiRate());
        values.put(uPFee, aLoan.getuPFee());
        values.put(lPFee, aLoan.getlPFee());
        values.put(mPAmount, aLoan.getmPAmount());
        values.put(aTerms, aLoan.getaTerms());
        values.put(lAmount, aLoan.getlAmount());
        values.put(pPeriod, aLoan.getpPeriod());
        values.put(sDOAgreement, aLoan.getsDOAgreement());
        values.put(cId, aLoan.getcId());

        try {
            numRecordsInserted = myDb.insert(loanTB, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRecordsInserted;
    }

    public long insertIntoInvestorTb(Investor anInvestor) {
        myDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(inId, anInvestor.getInId());
        values.put(pRLevel, anInvestor.getpRLevel());
        values.put(sOFunds, anInvestor.getsOFunds());
        values.put(icbAcc, anInvestor.getcBAccount());

        try {
            numRecordsInserted = myDb.insert(investorTB, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRecordsInserted;
    }
}
