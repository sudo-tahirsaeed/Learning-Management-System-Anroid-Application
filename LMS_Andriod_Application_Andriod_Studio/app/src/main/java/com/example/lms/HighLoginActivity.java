package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class HighLoginActivity extends AppCompatActivity  {

    String regnoD, passD;
    TextView regno,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_login);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        regno = findViewById(R.id.loginreg);

        pass = findViewById(R.id.loginpass);



        Button loginButton = findViewById(R.id.loginbtnx);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regnoD = regno.getText().toString();
                passD = pass.getText().toString();
                    if(regnoD.equals("admin") && passD.equals("admin")) {
                        Intent i = new Intent(HighLoginActivity.this, higherAuthDashboard.class);
                        // i.putExtra("reg", "ok");
                        startActivity(i);
                    }
                }

        });


   }
}


