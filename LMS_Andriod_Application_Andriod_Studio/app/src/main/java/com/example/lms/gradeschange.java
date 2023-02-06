package com.example.lms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class gradeschange extends AppCompatActivity {
    private SeekBar sb;
    public String sub,rnf;
    String sbf;
    int t=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradeschange);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView rn=findViewById(R.id.regnocg);
        Button ok = findViewById(R.id.buttongc);
        TextView slider= findViewById(R.id.gradegc);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sub = extras.getString("rega");
        }
        sb = (SeekBar) findViewById(R.id.cgsb);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int val = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                val = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(val==1) {
                    slider.setText("A");
                    sbf = "A";
                }
                if(val==2) {
                    slider.setText("B");
                    sbf = "B";
                }
                if(val==3) {
                    slider.setText("C");
                    sbf = "C";
                }
                if(val==4) {
                    slider.setText("D");
                    sbf = "D";
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rnf=rn.getText().toString();

                shodaa sx = new shodaa();
                sx.execute("");

            }
        });

    }
    public class shodaa extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {


            try {

                //connection
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.18.154:3306/lms", "max", "max123");
                Statement st = con.createStatement();

                Statement stmt = con.createStatement();

                String x= "UPDATE `coursesattendance` SET  `grade` = "+"'"+sbf  +"'"+" WHERE regno = "+"'"+rnf+"'"+" AND courseid ="+"'"+sub+"'"+";";

                stmt.executeUpdate (x);
                System.out.println("UPDATEDDD GRADES SUCESSFULLY "+ rnf +" "+ sub);
                t=1;

            } catch (Exception e) {
                System.out.println(e);
            }
            return "";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(t==1)
            {
                String sa="GRADES UPDATED";
                Toast.makeText(gradeschange.this, sa, Toast.LENGTH_SHORT).show();
                Intent i = new Intent( gradeschange.this, TeacherDashboard.class);
                i.putExtra("reg","OK");
                startActivity(i);
            }

        }
    }
}