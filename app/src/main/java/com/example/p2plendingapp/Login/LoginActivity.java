package com.example.p2plendingapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2plendingapp.Borrower.MainDashboard;
import com.example.p2plendingapp.Database.DBHelper;
import com.example.p2plendingapp.General.ProfileSelection;
import com.example.p2plendingapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView signup, forgot;
    Button login;
    DBHelper DB;
    CheckBox checkBox;
    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.inputUsername);
        password = findViewById(R.id.inputPassword);
        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.txtViewSignup);
        checkBox = findViewById(R.id.cbSignIn);
        forgot = findViewById(R.id.textViewForgot);
        isChecked = false;
        DB = new DBHelper(this);

        //store state of checkbox in shared preferences
        checkBox.setOnCheckedChangeListener(((compoundButton, b) -> {
            SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("isChecked", b);
            editor.commit();
        }));

        //get the state of checkbox
        SharedPreferences settings1 = getSharedPreferences("PREFS_NAME", 0);
        isChecked = settings1.getBoolean("isChecked", false);

        if (isChecked) {
            Intent intent = new Intent(getApplicationContext(), ProfileSelection.class);
            startActivity(intent);
        } else {
            login.setOnClickListener(view -> {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Input cannot be empty. Please input username or password correctly.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuser = DB.CheckUsername(user);
                    if (checkuser == true) {
                        Boolean checkuserpass = DB.CheckUsernamePassword(user, pass);
                        if (checkuserpass == true) {
                            Toast.makeText(LoginActivity.this, "Successfully logged in.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ProfileSelection.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Username does not exist. Please create an account.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            signup.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            });

            forgot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

}