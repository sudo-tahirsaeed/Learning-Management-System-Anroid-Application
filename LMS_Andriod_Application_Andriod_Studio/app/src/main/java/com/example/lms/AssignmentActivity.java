package com.example.lms;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class AssignmentActivity extends AppCompatActivity {

    String link = " ";
    String val1 = " ";
    TextView linktxt;

    String welcomename = " ";
    String val = " ";
    TextView name;

    String rollno = " ";
    String val2 = " ";
    TextView regno1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        name = findViewById(R.id.nameD);
        regno1 = findViewById(R.id.regnoC);
        linktxt = findViewById(R.id.assignLinkD);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            val = extras.getString("reg");
            System.out.println(val);

        }



        showz s= new showz();
        s.execute("");

        showreg a = new showreg();
        a.execute("");

        showlink b = new showlink();
        b.execute("");





    }

    public class showz extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {


            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.18.154:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT name FROM `registration` WHERE regno=" + "'" + val + "'" + ";";
                System.out.println("Dashboard val="+val);
                ResultSet rs = stmt.executeQuery(query);
                ResultSet rs1 = rs;
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

                int columnsNumber = rsmd.getColumnCount();
                String g[] = new String[40];
                int o = 0;
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {

                        String columnValue = rs.getString(i);


                        String columnValue1 = rs1.getString(i);

                        //System.out.print(columnValue1 );

                        g[o] = columnValue1;
                        o++;
                    }


                }
                System.out.println("\n");

                for (int i = 0; i < g.length; i++) {
                    if (g[i] != null) {
                        System.out.print(g[i]);
                        welcomename = g[i];


                    }

                }
            }
            catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }

            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.18.154:3306:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT regno FROM `registration` WHERE regno=" + "'" + val + "'" + ";";
                ResultSet rs = stmt.executeQuery(query);
                ResultSet rs1 = rs;
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

                int columnsNumber = rsmd.getColumnCount();
                String g[] = new String[40];
                int o = 0;
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {

                        String columnValue = rs.getString(i);


                        String columnValue1 = rs1.getString(i);

                        //System.out.print(columnValue1 );

                        g[o] = columnValue1;
                        o++;
                    }


                }
                System.out.println("\n");

                for (int i = 0; i < g.length; i++) {
                    if (g[i] != null) {
                        System.out.print(g[i]);
                        rollno = g[i];


                    }

                }
            }
            catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }


            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.18.154:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT link FROM `assignments`;";
                ResultSet rs = stmt.executeQuery(query);
                ResultSet rs1 = rs;
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

                int columnsNumber = rsmd.getColumnCount();
                String g[] = new String[40];
                int o = 0;
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {

                        String columnValue = rs.getString(i);


                        String columnValue1 = rs1.getString(i);

                        //System.out.print(columnValue1 );

                        g[o] = columnValue1;
                        o++;
                    }


                }
                System.out.println("\n");

                for (int i = 0; i < g.length; i++) {
                    if (g[i] != null) {
                        System.out.print("link "+g[i]);
                        link = g[i];


                    }

                }
            }
            catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }

            return "";


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            welcomename=welcomename.toUpperCase();
            name.setText(welcomename);
            rollno=rollno.toUpperCase();
            regno1.setText(rollno);
            link=link.toUpperCase();
            linktxt.setText(link);
        }
    }

    public class showreg extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {



            return "";


        }
    }

    public class showlink extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {




            return "";


        }
    }


}