package com.example.p2plendingapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p2plendingapp.Borrower.MainDashboard;
import com.example.p2plendingapp.Database.DBHelper;
import com.example.p2plendingapp.General.ProfileSelection;
import com.example.p2plendingapp.Model.User;
import com.example.p2plendingapp.R;

public class SignupActivity extends AppCompatActivity {

    EditText username, firstname, lastname, email, password, repassword;
    Button signup;
    DBHelper DB;

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

        DB = new DBHelper(this);

        signup.setOnClickListener(view -> {
            String user = username.getText().toString();
            String email = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();
            String fname = firstname.getText().toString();
            String lname = lastname.getText().toString();

            if (user.equals("") || pass.equals("") || repass.equals("") || fname.equals("") || lname.equals("")) {
                Toast.makeText(SignupActivity.this, "All information must be filled. Try again!", Toast.LENGTH_SHORT).show();
            } else {
                if (fname.matches(".*\\d.*") || lname.matches(".*\\d.*")) {
                    Toast.makeText(SignupActivity.this, "Name contains number! Please input alphabetical values.", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.length() > 11) {
                        if (pass.equals(repass)) {
                            Boolean checkuser = DB.CheckUsername(user);
                            if (checkuser == false) {
                                Boolean insert = DB.InsertData(user, email, pass, fname, lname);
                                if (insert == true) {
                                    DB.addUser(new User(user, email, pass, fname, lname));
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
                        Toast.makeText(SignupActivity.this, "Password has to be 12 characters minimum.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}