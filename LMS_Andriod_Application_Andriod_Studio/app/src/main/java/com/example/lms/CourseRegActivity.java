package com.example.lms;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CourseRegActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    ArrayAdapter<String> adapter4;


    private Spinner spinner;
    String cs="be computer science";
    String me="be mechanical engineering";
    String be= "be electrical engineering";
    String bba="business administration";
    String archi="bs architecture";

    String course_;
    String welcomename = " ";
    String val = " ";
    TextView name;

    String courseStr;

    String rollno = " ";
    String val2 = " ";
    TextView regno1;

    String course,attendance="0",grade="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_reg);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Random a1=new Random();
        int xaa=a1.nextInt(4);
        if(xaa==0)
            grade="A";
        else if(xaa==1)
            grade="B";
        else if(xaa==2)
            grade="C";
        else if(xaa==3)
            grade="D";

        Random a=new Random();
        int xa=a.nextInt(90);
        attendance=String.valueOf(xa);

        name = findViewById(R.id.nameD);
        regno1 = findViewById(R.id.regnoC);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            val = extras.getString("reg");
            System.out.println(val);

        }

        Bundle extra1 = getIntent().getExtras();
        if (extra1 != null) {
            courseStr = extra1.getString("crs");
            System.out.println("course "+courseStr);

        }


        showz s= new showz();
        s.execute("");


        Button coursebtn = findViewById(R.id.Regbtn);

        coursebtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Toast.makeText(RegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();

                spinner = findViewById(R.id.spinner);
                course_ = spinner.getSelectedItem().toString().toLowerCase();

//
                showreg aa=new showreg();
                aa.execute("");

            }
        });
//



            spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> Mech = new ArrayList<String>();
        Mech.add("Mechanical Engineering Courses");
        Mech.add("System Modelling");
        Mech.add("Structural Materials");
        Mech.add("Applied Physics");
        Mech.add("Calculus");
        Mech.add("Intro To Space Science");
        Mech.add("Engineering Mathematics");
        Mech.add("Engineering Chemistry");
        Mech.add("Machine Design");
        Mech.add("Mechanical Vibrations");
        Mech.add("Business and Entrepreneurship");


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Mech);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> CS = new ArrayList<String>();
        CS.add("Computer Science Courses");
        CS.add("Programming Fundamentals");
        CS.add("Database");
        CS.add("Operating System");
        CS.add("Introduction To Computer Technology");
        CS.add("Data Structures");
        CS.add("Artificial Intelligence");
        CS.add("Cyber Security");
        CS.add("Object Oriented Programming");
        CS.add("Information Technology");
        CS.add("Information Security");


        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CS);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> BBA = new ArrayList<String>();
        BBA.add("Business Administration Courses");
        BBA.add("IT in Business");
        BBA.add("Fundamentals of Accounting ");
        BBA.add("Business Math");
        BBA.add("Financial Accounting");
        BBA.add("Organizational Behaviour");
        BBA.add("Consumer Behaviour");
        BBA.add("Business Finance");
        BBA.add("Macroeconomics");
        BBA.add("Operations Management");
        BBA.add("Entrepreneurship");
        BBA.add("Business Ethics");


        //adapter = ArrayAdapter.createFromResource(this, R.array.CScourses, android.R.layout.simple_spinner_item);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,BBA);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        List<String> EE = new ArrayList<String>();
        EE.add("Electrical Engineering Courses");
        EE.add("Calculus");
        EE.add("Engineering Drawing");
        EE.add("Intro to Information Technology");
        EE.add("Linear Circuit Analysis");
        EE.add("Workshop Practice");
        EE.add("Differential Equations");
        EE.add("Programming Language");
        EE.add("Digital Logic Design");
        EE.add("Electromagnetic Field Theory");
        EE.add("Engineering Management");
        EE.add("Business and Entrepreneurship");


        //adapter = ArrayAdapter.createFromResource(this, R.array.CScourses, android.R.layout.simple_spinner_item);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,EE);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> ARCHI = new ArrayList<String>();
        ARCHI.add("Architecture Courses");
        ARCHI.add("History and Theory of Art and Culture");
        ARCHI.add("Foundation Studio");
        ARCHI.add("Architectural Studio");
        ARCHI.add("Material and Construction");
        ARCHI.add("Structures for Architects");
        ARCHI.add("Environment and Energy");
        ARCHI.add("Digital Tools for Architects");
        ARCHI.add("Cultural Heritage");
        ARCHI.add("Professional Practice");
        ARCHI.add("Thesis Design");
        ARCHI.add("Sustainable Design");



        adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ARCHI);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        if(courseStr.equals(me)) {
            System.out.println("passed");
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        }
        if(courseStr.equals(cs)) {
            System.out.println("passed1");
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        }
         if(courseStr.equals(bba)) {
            System.out.println("passed2");
            spinner.setAdapter(adapter2);
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        }
         if(courseStr.equals(be)) {
            System.out.println("passed3");
            spinner.setAdapter(adapter3);
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        }
         if(courseStr.equals(archi)) {
            System.out.println("passed4");
            spinner.setAdapter(adapter4);
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        }
        else{
            System.out.println("Not Passed");
        }




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
//        Toast.makeText(getApplicationContext(),choice, Toast.LENGTH_LONG).show();
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) adapterView.getChildAt(0)).setTextSize(20);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
                        "jdbc:mysql://192.168.18.154:3306/lms", "max", "max123");
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


            return "";


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            welcomename=welcomename.toUpperCase();
            name.setText(welcomename);
            rollno=rollno.toUpperCase();
            regno1.setText(rollno);


        }
    }


    public class showreg extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(CourseRegActivity.this, "REGISTERED SUCCESSFULLY ", Toast.LENGTH_SHORT).show();
//            Intent ia = new Intent( CourseRegActivity.this, DashboardSTDActivity.class);
//            ia.putExtra("done",val);
//            startActivity(ia);
        }
        @Override
        protected String doInBackground(String... strings) {

            if( !(val.isEmpty()) && !(course_.isEmpty()))
            {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://192.168.18.154:3306/lms", "max", "max123");
                    String insert=("INSERT INTO `coursesattendance` (`regno`, `courseid`, `attendance`, `grade`) VALUES (?,?,?,?);");
                    PreparedStatement preparedStmt = con.prepareStatement(insert);
                    preparedStmt.setString (1, val);
                    preparedStmt.setString (2, course_);
                    preparedStmt.setString (3, attendance);
                    preparedStmt.setString(4, grade);
                    preparedStmt.execute();
                    con.close();
                    System.out.println(val);
                    System.out.println(course_);
                    System.out.println(attendance);
                    System.out.println(grade);
                    // TOAST
                    // DISPLAY
                    String text="REGISTERED SUCCESSFULLY";
//                    Toast.makeText(CourseRegActivity.this,text , Toast.LENGTH_SHORT).show();

                    Log.i("mylog", "Registered Successfully with Database");
                    //startActivity(new Intent(RegisterActivity.this,LoginActivity.class ));

                    return "OK";

                }
                catch (Exception e) {
                    // TOAST DISPLAY
//                    String text="Cant Connect to Database";
//                    Toast.makeText(RegisterActivity.this,text , Toast.LENGTH_SHORT).show();

                    Log.i("mylog", "CANT CONNECT TO DATABASE: "+e);
                    return "CANT REGISTER : " + e.toString();
                }
            }
            else{
                // TOAST DISPLAY
//                String text="Please Fill All Fields";
//                Toast.makeText(RegisterActivity.this,text , Toast.LENGTH_SHORT).show();

                Log.i("mylog", "PLEASE FILL ALL FIELDS ");
                return "FAILED TO REGISTER";

            }
        }
    }





}