package com.example.racheli.equationsolver;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/* This  application solves linear equations.
A linear equation is an equation of the form -
Ax = b
Where a and b are real numbers and a ≠ 0.
The solution of the equation is the x value that satisfies the equation, ie x = a / b

 */
public class EquationSolverActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView lbltheresult;
    private TextView lblInNormal;
    private EditText edtEquID;
    private Button cmdCalacClick;
    private LinearEquation linearEquation;
    private LinearFragment linearFragment;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_solver);

        lbltheresult = (TextView) findViewById(R.id.lbltheresult);
        lblInNormal = (TextView) findViewById(R.id.lblInnormal);
        edtEquID = (EditText) findViewById(R.id.edtEquID);
        cmdCalacClick = (Button) findViewById(R.id.cmdCalacClick);

        cmdCalacClick.setOnClickListener(this);
        sp = getSharedPreferences("Equation", MODE_PRIVATE);
        sp = getSharedPreferences("solve", MODE_PRIVATE);
        sp = getSharedPreferences("lblInNormal", MODE_PRIVATE);

        editor = sp.edit();
        String tvalue= sp.getString("Equation","");
        String solve= sp.getString("solve","");
        String normal=sp.getString("lblInNormal","");

        lbltheresult.setText(solve);
        lblInNormal.setText(normal);
        edtEquID.setText(tvalue);



    }

    @Override
    public void onClick(View view) {
        String theEquation = edtEquID.getText().toString();//Retrieving information from the screen
        editor.putString("Equation", theEquation);
        editor.commit();
        if (theEquation.equals("") || theEquation.equals(""))
            Toast.makeText(this, "Please enter a valid Equation ", Toast.LENGTH_LONG).show();

        try {
            linearEquation= LinearEquation.parseEquation(theEquation);
        } catch (IllegalFormatException e) {
            e.printStackTrace();
        }
            double theResult =linearEquation.solve();
            String theResuleS=Double.toString(theResult);
        editor.putString("solve", theResuleS);
        editor.putString("lblInNormal", linearEquation.toString());
        editor.commit();
            lblInNormal.setText((CharSequence) linearEquation.toString());
            lbltheresult.setText(theResuleS);


    }
}
