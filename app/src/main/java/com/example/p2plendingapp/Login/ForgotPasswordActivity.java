package com.example.p2plendingapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p2plendingapp.Database.DBHelper;
import com.example.p2plendingapp.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText username;
    Button reset;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        username = findViewById(R.id.usernameReset);
        reset = findViewById(R.id.btnReset);
        DB = new DBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();

                Boolean checkuser = DB.CheckUsername(user);

                if(checkuser == true){
                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgotPasswordActivity.this,"User does not exists",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}