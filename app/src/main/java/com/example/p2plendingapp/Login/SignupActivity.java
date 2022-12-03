package com.example.p2plendingapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p2plendingapp.Database.DBHelper;
import com.example.p2plendingapp.Database.p2pLendingDB;
import com.example.p2plendingapp.General.ProfileSelection;
import com.example.p2plendingapp.Model.Account;
import com.example.p2plendingapp.Model.Customer;
import com.example.p2plendingapp.Model.User;
import com.example.p2plendingapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    EditText username, firstname, lastname, email, password, repassword, dob;
    Button signup;
    //p2pLendingDB DB;
    DBHelper DB;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        username = findViewById(R.id.inputUsername);
        firstname = findViewById(R.id.inputFirstName);
        lastname = findViewById(R.id.inputLastName);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        repassword = findViewById(R.id.inputPasswordConfirm);
        signup = findViewById(R.id.btnSignup);
        dob = findViewById(R.id.inputDOB);

        Calendar calendar = Calendar.getInstance();

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dob.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        DB = new DBHelper(this);
//        DB = new p2pLendingDB(this);

        signup.setOnClickListener(view -> {
            String user = username.getText().toString();
            String email = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();
            String fname = firstname.getText().toString();
            String lname = lastname.getText().toString();
            String dOb = dob.getText().toString();

            if (user.equals("") || pass.equals("") || email.equals("") || repass.equals("") || fname.equals("") || lname.equals("")) {
                Toast.makeText(SignupActivity.this, "All information must be filled. Try again!", Toast.LENGTH_SHORT).show();
            } else {
                if (fname.matches(".*\\d.*") || lname.matches(".*\\d.*")) {
                    Toast.makeText(SignupActivity.this, "Name contains number! Please input alphabetical values.", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.length() > 11) {
                        if (isValidPassword(pass)) {
                            if (pass.equals(repass)) {
                                Boolean checkuser = DB.CheckUsername(user);
                                if (checkuser == false) {
                                    Boolean insert = DB.InsertData(user, email, pass, fname, lname);
//                                    Boolean insert2 = DB.insertIntoCustomerTb2(dOb, fname, lname);
                                    if (insert == true /*&& insert2 == true*/) {
                                        DB.addUser(new User(user, pass, email, fname, lname));
//                                        DB.insertIntoCustomerTb(new Customer(dOb, fname, lname));
                                        Toast.makeText(SignupActivity.this, "Successfully registered.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), ProfileSelection.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(SignupActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(SignupActivity.this, "User exists! Try another username.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignupActivity.this, "Password does not match! Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "Password needs to have at least 1 Alphabet, 1 Number, and 1 Special Character", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Password has to be 12 characters minimum.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-13])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
}