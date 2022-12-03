package com.example.p2plendingapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2plendingapp.Database.DBHelper;
import com.example.p2plendingapp.R;

public class ResetActivity extends AppCompatActivity {

    TextView username;
    EditText pass, repass;
    Button btnconfirm;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        username = findViewById(R.id.usernameResetText);
        pass = findViewById(R.id.password_reset);
        repass = findViewById(R.id.repassword_reset);
        btnconfirm = findViewById(R.id.btnConfirm);
        DB = new DBHelper(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();

                if (password.equals(repassword)){

                    Boolean checkPassUpdate = DB.UpdatePassword(user, password);

                    if (checkPassUpdate){
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this,"Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ResetActivity.this,"Password Updated Unsuccessfully", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(ResetActivity.this,"Password does not match", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}