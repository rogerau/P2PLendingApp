package com.example.p2plendingapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.p2plendingapp.R;

public class EntranceActivity extends AppCompatActivity implements View.OnClickListener {

    Intent sIntent;
    TextView signup;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.textViewSignup);

        //Set up the listeners
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signin) {
            sIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(sIntent);
        } else {
            sIntent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(sIntent);

        }
    }
}