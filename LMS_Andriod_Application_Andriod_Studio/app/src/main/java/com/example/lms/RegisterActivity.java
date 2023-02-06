package com.example.lms;

import android.content.Intent;
import android.graphics.Color;
import java.util.regex.*;
import java.util.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
private Spinner spinner,spinner2;
String named,emaild,semd,passd,regnod,medissue,coursename;
int f=0,fx=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//already registered back to login

        TextView btn = findViewById(R.id.AlreadyReg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "LOGIN", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class ));
            }
        });
        spinner2=findViewById(R.id.courseName);

        spinner = findViewById(R.id.spinner);
        /// Register Button

        Button regb= findViewById(R.id.regbtn);
        regb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(RegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();

                TextView name=findViewById(R.id.loginreg);
                named= name.getText().toString().toLowerCase();
                TextView email=findViewById(R.id.regNo);
               emaild= email.getText().toString().toLowerCase();
                String regex = "^(.+)@(.+)$";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(emaild);
                if(matcher.matches()==false){
                    Toast.makeText(RegisterActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();

                }
                else {



                        TextView sem = findViewById(R.id.InputCourse);
                        semd = sem.getText().toString().toLowerCase();

                        TextView regno = findViewById(R.id.InputRegNo);
                        regnod = regno.getText().toString().toLowerCase();

                        spinner = findViewById(R.id.spinner);
                        medissue = spinner.getSelectedItem().toString().toLowerCase();

                        spinner2 = findViewById(R.id.courseName);
                        coursename = spinner2.getSelectedItem().toString().toLowerCase();

                        if(medissue.equals("other"))
                        {
                            TextView ot = findViewById(R.id.othermedissue);
                            medissue =ot.getText().toString().toLowerCase();

                        }
                        TextView password = findViewById(R.id.InputPassword);
                        passd = password.getText().toString().toLowerCase();

                        showx ss = new showx();
                        ss.execute("");

                    }

                }


        });


         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.issues, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner2.setAdapter(adapter1);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
//        Toast.makeText(getApplicationContext(),choice, Toast.LENGTH_LONG).show();
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) adapterView.getChildAt(0)).setTextSize(20);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class showx extends AsyncTask<String,String,String> {




        @Override
        protected String doInBackground(String... strings) {
            if( !(named.isEmpty()) && !(emaild.isEmpty()) && !(semd.isEmpty())  && !(regnod.isEmpty())  && !(medissue.isEmpty()) && !(passd.isEmpty())    )
            {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://192.168.18.154:3306/lms", "max", "max123");
                String insert=("INSERT INTO `registration` (`name`, `email`, `semester`, `regno`, `medical_issue`, `password`,`coursename`) VALUES (?,?,?,?,?,?,?);");
                PreparedStatement preparedStmt = con.prepareStatement(insert);
                preparedStmt.setString (1, named);
                preparedStmt.setString (2, emaild);
                preparedStmt.setString   (3, semd);
                preparedStmt.setString(4, regnod);
                preparedStmt.setString    (5, medissue);
                preparedStmt.setString    (6, passd);
                preparedStmt.setString(7,coursename);
                preparedStmt.execute();
                con.close();
                // TOAST DISPLAY
//                    String text="REGISTERED SUCESSFULLY";
//                    Toast.makeText(RegisterActivity.this,text , Toast.LENGTH_SHORT).show();

                    Log.i("mylog", "Registered Successfully with Database");
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class ));
                    fx=2;
                    return "OK";

                }
                catch (Exception e) {
                    // TOAST DISPLAY
//                    String text="Cant Connect to Database";
//                    Toast.makeText(RegisterActivity.this,text , Toast.LENGTH_SHORT).show();

                    Log.i("mylog", "CANT CONNECT TO DATABASE: "+e);
                    return "CANT REGISTER : " + e.toString();
                }}
            else{

                f=2;
                Log.i("mylog", "PLEASE FILL ALL FIELDS ");
                return "FAILED TO REGISTER";

            }
            }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(f==2)
            {
                Toast.makeText(RegisterActivity.this, "Please Fill All Fileds", Toast.LENGTH_SHORT).show();
            }
            if(fx==2)
            {
                Toast.makeText(RegisterActivity.this, "Registration Successful! ", Toast.LENGTH_SHORT).show();

            }
        }
    }
    }

