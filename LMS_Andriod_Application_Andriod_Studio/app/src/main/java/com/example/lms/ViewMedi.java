package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class ViewMedi extends AppCompatActivity {
    private int AsyncTaskCount = 0;     //tracks number of requests completed
    private int AsyncTaskRequested = 0; //tracks number of requests
    private ProgressBar spinner;
    ListView myListView,attlistv;
    int fetch=0;
    //we are requesting two asynctasks
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> arrayAdapter2;
    String val,subject;
    ArrayList<Integer> red = new ArrayList<Integer>();

    ArrayList<Integer> green = new ArrayList<Integer>();


    ArrayList<String> studentnames = new ArrayList<>();
    ArrayList<String> sturegno = new ArrayList<>();
    ArrayList<String> attend = new ArrayList<>();
    ArrayList<String> medissue = new ArrayList<>();
    ArrayList<String> regnumber = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medi);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        myListView = findViewById(R.id.myListView);
        attlistv = findViewById(R.id.listviewattendence);


        spinner = (ProgressBar)findViewById(R.id.progressbar);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            val = extras.getString("regaxa");
        }
        AsyncTaskRequested = 1;
       showzaax zaa =new showzaax();
        zaa.execute("");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medissue);

        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, regnumber)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row = super.getView(position, convertView, parent);
//                int xa=Integer.parseInt(getItem(position));
//                if(xa<70)
//                {
//                    // do something change color
//                    row.setBackgroundColor (Color.rgb(255,82,82)); // some color
//                }
//                else
//                {
//                    // default state
//                    row.setBackgroundColor (Color.rgb(2,174,72)); // default coloe
//                }
                return row;
            }
        };


    }


    public class showzaax extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            AsyncTaskCount++;
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            spinner.setVisibility(View.VISIBLE); //set the bar visible

            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.100.2:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT courseteaching FROM `teacherregistration` WHERE regnot=" + "'" + val + "'" + ";";
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

                        subject=g[i];
                    }

                }
            } catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }






            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.100.2:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT regno FROM `coursesattendance` WHERE courseid=" + "'" + subject + "'" + ";";
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
                        sturegno.add(g[i]);


                    }

                }
                System.out.println("REG NO:"+sturegno);
            } catch (Exception e) {

            }


            /////// names ///



            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.100.2:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();

                for(int a=0 ; a<sturegno.size();a++ )
                {
                    String query = "SELECT regno FROM `registration` WHERE regno=" + "'" + sturegno.get(a) + "'" +";";

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

                           regnumber.add(g[i]);
                        }

                    }
                }
                System.out.println("ref:"+regnumber);
            } catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }
            ///attendance ///
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.100.2:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();

                for(int axa=0 ; axa<sturegno.size();axa++ )
                {
                    String query = "SELECT medical_issue FROM `registration` WHERE regno=" + "'" + sturegno.get(axa) + "'" +";";
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

                            medissue.add(g[i]);
                        }

                    }
                }

            } catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }
//            for(int ax=0 ; ax<sturegno.size();ax++ )
//            {
//                int x =Integer.parseInt(attend.get(ax));
//                if(x<70)
//                    red.add(ax);

//                System.out.println(sturegno.get(ax));
//
//            }
//            for(int ax=0 ; ax<studentnames.size();ax++ )
//            {
//
//
//
//                System.out.println(studentnames.get(ax));
//
//            }
//            for(int ax=0 ; ax<red.size();ax++ ) {
//
//
//                System.out.println("RED " + red.get(ax));
            //}
            return "";


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(AsyncTaskCount == AsyncTaskRequested) {
                fetch=1;
                spinner.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                myListView.setAdapter(arrayAdapter);

                attlistv.setAdapter(arrayAdapter2);



            }
        }
    }



}